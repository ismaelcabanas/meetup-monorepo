package cabanas.garcia.ismael.meetup.payments.application.buysubscription

import cabanas.garcia.ismael.meetup.payment.application.buysubscription.BuySubscriptionCommandHandler
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.Money
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentStatus
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event.SubscriptionPaymentCreated
import cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments.FakerSubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.shared.infrastructure.service.DateProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BuySubscriptionCommandHandlerShould {
    private lateinit var commandHandler: BuySubscriptionCommandHandler

    private var repository: SubscriptionPaymentRepository = FakerSubscriptionPaymentRepository()
    private var eventBus: EventBus = mockk(relaxed = true)
    private var dateProvider: DateProvider = mockk()

    @BeforeEach
    fun setUp() {
        every { dateProvider.now() } returns Instant.parse(SOME_DATE)

        commandHandler = BuySubscriptionCommandHandler(repository, dateProvider, eventBus)
    }

    @Test
    fun `GIVEN a valid buy subscription command WHEN execute command handler THEN subscription payment is added`() {
        val command = BuySubscriptionCommandMother.random(
            date = Instant.parse(SOME_DATE)
        )
        val expectedSubscriptionPayment = SubscriptionPayment(
            SubscriptionPaymentId(command.paymentId),
            PayerId(command.payerId),
            SubscriptionPaymentStatus.WAITING_FOR_PAYMENT,
            SubscriptionType.valueOf(command.type),
            SubscriptionPeriod.valueOf(command.period),
            Money(command.value),
            Instant.parse(SOME_DATE)
        )

        commandHandler.handle(command)

        assertThat(repository.get(SubscriptionPaymentId(command.paymentId)))
            .isEqualTo(expectedSubscriptionPayment)
    }

    @Test
    fun `GIVEN a valid buy subscription command WHEN execute command handler THEN subscription payment created event is published`() {
        val command = BuySubscriptionCommandMother.random()
        val expectedEvent = SubscriptionPaymentCreated(
            command.paymentId,
            command.payerId,
            command.type,
            command.period,
            command.value
        )

        commandHandler.handle(command)

        verify {
            eventBus.publish(listOf(expectedEvent))
        }
    }

    companion object {
        private const val SOME_DATE = "2021-01-25T13:13:03Z"
    }
}
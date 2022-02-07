package cabanas.garcia.ismael.meetup.payments.application.buysubscription

import cabanas.garcia.ismael.meetup.payment.application.buysubscription.BuySubscriptionCommandHandler
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event.SubscriptionPaymentCreated
import cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments.FakerSubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BuySubscriptionCommandHandlerShould {
    private lateinit var commandHandler: BuySubscriptionCommandHandler

    private var repository: SubscriptionPaymentRepository = FakerSubscriptionPaymentRepository()
    private var eventBus: EventBus = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        commandHandler = BuySubscriptionCommandHandler(repository, eventBus)
    }

    @Test
    fun `GIVEN a valid buy subscription command WHEN execute command handler THEN subscription payment is added`() {
        val command = BuySubscriptionCommandMother.random()
        val expectedSubscriptionPayment = SubscriptionPayment(
            SubscriptionPaymentId(command.paymentId)
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
}
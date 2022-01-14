package cabanas.garcia.ismael.meetup.payments.application.createpayer

import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommandHandler
import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.event.PayerCreated
import cabanas.garcia.ismael.meetup.payments.domain.FakePayerRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatePayerCommandHandlerShould {
    private var payerRepository = FakePayerRepository()
    private var eventBus: EventBus = mockk(relaxed = true)
    private lateinit var commandHandler: CreatePayerCommandHandler

    @BeforeEach
    fun setUp() {
        commandHandler = CreatePayerCommandHandler(payerRepository, eventBus)
    }

    @Test
    fun `GIVEN a valid create payer command WHEN execute command handler THEN payer is added`() {
        val command = CreatePayerCommandMother.random()
        val expectedPayer = Payer(
            PayerId(command.userId),
            command.email,
            command.firstName,
            command.lastName
        )

        commandHandler.handle(command)

        assertThat(payerRepository.get(PayerId(command.userId)))
            .isEqualTo(expectedPayer)
    }

    @Test
    fun `GIVEN a valid create payer command WHEN execute command handler THEN payer created event is published`() {
        val command = CreatePayerCommandMother.random()
        val expectedPayerCreated = PayerCreated(
            command.userId,
            command.email,
            command.firstName,
            command.lastName
        )

        commandHandler.handle(command)

        verify {
            eventBus.publish(listOf(expectedPayerCreated))
        }
    }
}
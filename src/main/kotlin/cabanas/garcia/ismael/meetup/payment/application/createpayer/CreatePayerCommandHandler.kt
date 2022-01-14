package cabanas.garcia.ismael.meetup.payment.application.createpayer

import cabanas.garcia.ismael.meetup.payment.domain.Payer
import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus

class CreatePayerCommandHandler(
    private val payerRepository: PayerRepository,
    private val eventBus: EventBus
) : CommandHandler<CreatePayerCommand> {
    override fun handle(command: CreatePayerCommand) {
        val payer = Payer.create(
            PayerId(command.userId),
            command.email,
            command.firstName,
            command.lastName
        )

        payerRepository.add(payer)

        eventBus.publish(payer.pullEvents())
    }
}
package cabanas.garcia.ismael.meetup.payment.infrastructure.messaging.consumer.spring

import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommand
import cabanas.garcia.ismael.meetup.shared.application.Command
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.NewUserRegistered
import org.springframework.context.event.EventListener

class CreatePayerOnUserRegistered(
    private val commandBus: CommandBus
) {
    @EventListener
    fun handle(userRegistered: NewUserRegistered) {
        commandBus.dispatch(userRegistered.toCommand())
    }
}

private fun NewUserRegistered.toCommand(): Command =
    CreatePayerCommand(
        this.id,
        this.email,
        this.firstName,
        this.lastName
    )

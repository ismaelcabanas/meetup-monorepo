package cabanas.garcia.ismael.meetup.useraccess.infrastructure.eventhandler.spring

import cabanas.garcia.ismael.meetup.shared.application.Command
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.application.createuser.CreateUserByUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationConfirmed
import org.springframework.context.event.EventListener

class UserEventHandler(
    private val commandBus: CommandBus
) {
    @EventListener
    fun handleUserRegistrationConfirmed(domainEvent: UserRegistrationConfirmed) {
        commandBus.dispatch(toCommand(domainEvent))
    }

    private fun toCommand(domainEvent: UserRegistrationConfirmed): Command =
        CreateUserByUserRegistrationCommand(domainEvent.userRegistrationId)
}
package cabanas.garcia.ismael.meetup.shared.application

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommand
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.newRegistration.CreateUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.newRegistration.CreateUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.confirmRegistration.ConfirmUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.confirmRegistration.ConfirmUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import kotlin.reflect.KClass

class InMemoryCommandBus(
    userRegistrationRepository: UserRegistrationRepository,
    usersCounter: UsersCounter,
    eventBus: EventBus,
    authenticateUserCommandHandler: AuthenticateUserCommandHandler
) : CommandBus {
    private val commandHandlers: MutableMap<KClass<out Command>, in CommandHandler<out Command>> = mutableMapOf()

    init {
        commandHandlers[CreateUserRegistrationCommand::class] = CreateUserRegistrationCommandHandler(
            userRegistrationRepository,
            usersCounter,
            eventBus
        )
        commandHandlers[ConfirmUserRegistrationCommand::class] = ConfirmUserRegistrationCommandHandler(
            userRegistrationRepository,
            eventBus
        )
        commandHandlers[AuthenticateUserCommand::class] = authenticateUserCommandHandler
    }

    override fun dispatch(command: Command) {
        val commandHandler = commandHandlers[command::class] as CommandHandler<Command>

        commandHandler.handle(command)
    }
}
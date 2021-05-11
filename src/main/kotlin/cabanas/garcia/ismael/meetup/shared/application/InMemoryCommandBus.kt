package cabanas.garcia.ismael.meetup.shared.application

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommand
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.newregistration.NewUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.newregistration.NewUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.confirmRegistration.ConfirmUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.confirmRegistration.ConfirmUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.createuser.CreateUserByUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.createuser.CreateUserByUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import kotlin.reflect.KClass

class InMemoryCommandBus(
    userRegistrationRepository: UserRegistrationRepository,
    usersCounter: UsersCounter,
    eventBus: EventBus,
    authenticateUserCommandHandler: AuthenticateUserCommandHandler,
    createUserByUserRegistrationCommandHandler: CreateUserByUserRegistrationCommandHandler
) : CommandBus {
    private val commandHandlers: MutableMap<KClass<out Command>, in CommandHandler<out Command>> = mutableMapOf()

    init {
        commandHandlers[NewUserRegistrationCommand::class] = NewUserRegistrationCommandHandler(
            userRegistrationRepository,
            usersCounter,
            eventBus
        )
        commandHandlers[ConfirmUserRegistrationCommand::class] = ConfirmUserRegistrationCommandHandler(
            userRegistrationRepository,
            eventBus
        )
        commandHandlers[AuthenticateUserCommand::class] = authenticateUserCommandHandler
        commandHandlers[CreateUserByUserRegistrationCommand::class] = createUserByUserRegistrationCommandHandler
    }

    override fun dispatch(command: Command) {
        val commandHandler = commandHandlers[command::class] as CommandHandler<Command>

        commandHandler.handle(command)
    }
}
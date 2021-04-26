package cabanas.garcia.ismael.meetup.shared.application

import cabanas.garcia.ismael.meetup.useraccess.application.rergistration.CreateUserRegistrationCommand
import cabanas.garcia.ismael.meetup.useraccess.application.rergistration.CreateUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter

class DummyCommandBus(
    userRegistrationRepository: UserRegistrationRepository,
    usersCounter: UsersCounter
) : CommandBus {
    private val commandHandler: CommandHandler

    init {
        commandHandler = CreateUserRegistrationCommandHandler(
            userRegistrationRepository,
            usersCounter
        )
    }

    override fun dispatch(command: CreateUserRegistrationCommand) {
        commandHandler.handle(command)
    }
}
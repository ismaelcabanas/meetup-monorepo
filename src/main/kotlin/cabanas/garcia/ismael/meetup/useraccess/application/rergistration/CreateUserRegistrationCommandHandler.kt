package cabanas.garcia.ismael.meetup.useraccess.application.rergistration

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationFactory
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter

class CreateUserRegistrationCommandHandler(
    private val userRegistrationRepository: UserRegistrationRepository,
    private val usersCounter: UsersCounter
) : CommandHandler {
    override fun handle(command: CreateUserRegistrationCommand) {
        val userRegistration = UserRegistrationFactory.registerNewUser(
            command.id,
            command.email,
            command.password,
            command.email,
            command.firstName,
            command.lastName,
            usersCounter
        )

        userRegistrationRepository.save(userRegistration)
    }
}
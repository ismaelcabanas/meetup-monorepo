package cabanas.garcia.ismael.meetup.useraccess.application.newregistration

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationFactory
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class NewUserRegistrationCommandHandler(
    private val userRegistrationRepository: UserRegistrationRepository,
    private val usersCounter: UsersCounter,
    private val eventBus: EventBus
) : CommandHandler<NewUserRegistrationCommand> {
    private var logger: Logger = LoggerFactory.getLogger(NewUserRegistrationCommandHandler::class.java)

    override fun handle(command: NewUserRegistrationCommand) {
        logger.info("New user registration $command")

        val userRegistration = UserRegistrationFactory.create(
            command.id,
            command.email,
            command.password,
            command.email,
            command.firstName,
            command.lastName
        )

        userRegistration.register(usersCounter)

        userRegistrationRepository.save(userRegistration)

        eventBus.publish(userRegistration.pullEvents())
    }
}
package cabanas.garcia.ismael.meetup.useraccess.application.createuser

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateUserByUserRegistrationCommandHandler(
    private val userRegistrationRepository: UserRegistrationRepository,
    private val userRepository: UserRepository,
    private val eventBus: EventBus
) : CommandHandler<CreateUserByUserRegistrationCommand> {
    private var logger: Logger = LoggerFactory.getLogger(CreateUserByUserRegistrationCommandHandler::class.java)

    override fun handle(command: CreateUserByUserRegistrationCommand) {
        logger.info("Create user $command")

        val userRegistration = userRegistrationRepository.findBy(UserRegistrationId(command.userRegistrationId))

        val user = userRegistration.createUser()

        userRepository.save(user)

        eventBus.publish(user.events())
    }
}
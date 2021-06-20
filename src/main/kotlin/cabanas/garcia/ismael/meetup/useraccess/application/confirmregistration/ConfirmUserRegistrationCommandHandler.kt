package cabanas.garcia.ismael.meetup.useraccess.application.confirmregistration

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConfirmUserRegistrationCommandHandler(
    private val userRegistrationRepository: UserRegistrationRepository,
    private val eventBus: EventBus
) : CommandHandler<ConfirmUserRegistrationCommand> {
    private var logger: Logger = LoggerFactory.getLogger(ConfirmUserRegistrationCommandHandler::class.java)

    override fun handle(command: ConfirmUserRegistrationCommand) {
        logger.info("Confirm user registration $command")

        val userRegistration = userRegistrationRepository.findBy(UserRegistrationId(command.userRegistrationId))

        userRegistration.confirm()

        userRegistrationRepository.save(userRegistration)

        eventBus.publish(userRegistration.pullEvents())
    }
}
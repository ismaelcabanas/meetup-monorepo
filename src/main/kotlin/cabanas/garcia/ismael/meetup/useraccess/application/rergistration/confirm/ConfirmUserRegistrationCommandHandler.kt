package cabanas.garcia.ismael.meetup.useraccess.application.rergistration.confirm

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository

class ConfirmUserRegistrationCommandHandler(
    private val userRegistrationRepository: UserRegistrationRepository,
    private val eventBus: EventBus
) : CommandHandler<ConfirmUserRegistrationCommand> {
    override fun handle(command: ConfirmUserRegistrationCommand) {
        val userRegistration = userRegistrationRepository.findBy(UserRegistrationId(command.userRegistrationId))

        userRegistration.confirm()

        userRegistrationRepository.save(userRegistration)

        eventBus.publish(userRegistration.events())
    }
}
package cabanas.garcia.ismael.meetup.useraccess.application.authentication

import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserAuthenticated
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository

class AuthenticateUserCommandHandler(
    private val userRepository: UserRepository,
    private val eventBus: EventBus
) : CommandHandler<AuthenticateUserCommand> {
    override fun handle(command: AuthenticateUserCommand) {
        val user = userRepository.findBy(command.login, command.password)

        eventBus.publish(
            listOf(
                UserAuthenticated(user.login, user.password)
            )
        )
    }
}
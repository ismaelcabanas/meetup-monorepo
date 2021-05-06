package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandHandlerConfiguration {
    @Bean
    fun authenticationCommandHandler(userRepository: UserRepository, eventBus: EventBus) =
        AuthenticateUserCommandHandler(userRepository, eventBus)
}
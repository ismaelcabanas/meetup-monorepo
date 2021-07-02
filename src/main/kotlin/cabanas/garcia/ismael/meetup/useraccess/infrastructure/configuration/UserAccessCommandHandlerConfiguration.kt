package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.createuser.CreateUserByUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserAccessCommandHandlerConfiguration {
    @Bean
    fun authenticationCommandHandler(userRepository: UserRepository, eventBus: EventBus) =
        AuthenticateUserCommandHandler(userRepository, eventBus)

    @Bean
    fun createUserByUserRegistrationCommandHandler(
        userRegistrationRepository: UserRegistrationRepository,
        userRepository: UserRepository,
        eventBus: EventBus
    ) = CreateUserByUserRegistrationCommandHandler(userRegistrationRepository, userRepository, eventBus)
}
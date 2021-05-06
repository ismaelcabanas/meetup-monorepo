package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.useraccess.domain.user.InMemoryUserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.InMemoryUserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun userRegistrationRepository(): UserRegistrationRepository = InMemoryUserRegistrationRepository()

    @Bean
    fun userRepository(): UserRepository = InMemoryUserRepository()
}

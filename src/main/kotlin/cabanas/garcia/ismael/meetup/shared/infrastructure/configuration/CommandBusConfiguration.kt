package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.application.InMemoryCommandBus
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun dummyCommandBus(
        userRegistrationRepository: UserRegistrationRepository,
        usersCounter: UsersCounter,
        eventBus: EventBus
    ) = InMemoryCommandBus(userRegistrationRepository, usersCounter, eventBus)
}
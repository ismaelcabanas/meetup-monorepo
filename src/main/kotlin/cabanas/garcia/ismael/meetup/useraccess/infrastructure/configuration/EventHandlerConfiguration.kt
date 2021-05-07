package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.infrastructure.eventhandler.spring.UserEventHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventHandlerConfiguration {
    @Bean
    fun userRegistrationEventHandler(
        commandBus: CommandBus
    ) =
        UserEventHandler(
            commandBus
        )
}
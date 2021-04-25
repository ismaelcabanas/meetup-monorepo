package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.application.DummyCommandBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun dummyCommandBus() = DummyCommandBus()
}
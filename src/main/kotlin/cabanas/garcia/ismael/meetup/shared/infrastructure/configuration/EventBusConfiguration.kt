package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.domain.service.DummyEventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusConfiguration {
    @Bean
    fun eventBus() = DummyEventBus()
}
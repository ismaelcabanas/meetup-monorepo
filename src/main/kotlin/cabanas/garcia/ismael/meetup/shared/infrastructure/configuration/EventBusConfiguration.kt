package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.infrastructure.eventbus.spring.SpringEventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusConfiguration {
    @Bean("springEventBus")
    fun eventBus(applicationEventPublisher: ApplicationEventPublisher) = SpringEventBus(applicationEventPublisher)
}
package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.shared.infrastructure.commandbus.spring.Registry
import cabanas.garcia.ismael.meetup.shared.infrastructure.commandbus.spring.SpringCommandBus
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun springCommandBus(applicationContext: ApplicationContext): CommandBus =
        SpringCommandBus(Registry(applicationContext))
}
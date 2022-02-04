package cabanas.garcia.ismael.meetup.shared.infrastructure.framework.configuration

import cabanas.garcia.ismael.meetup.shared.application.SuccessCommandBus
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class ControllerTestConfiguration {
    @Bean
    fun commandBus() = SuccessCommandBus()
}
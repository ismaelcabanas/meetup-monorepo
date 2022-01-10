package cabanas.garcia.ismael.meetup.payment.infrastructure.configuration

import cabanas.garcia.ismael.meetup.payment.infrastructure.messaging.consumer.spring.CreatePayerOnUserRegistered
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentEventHandlerConfiguration {
    @Bean
    fun createPayerOnUserRegistered(commandBus: CommandBus) =
        CreatePayerOnUserRegistered(commandBus)
}
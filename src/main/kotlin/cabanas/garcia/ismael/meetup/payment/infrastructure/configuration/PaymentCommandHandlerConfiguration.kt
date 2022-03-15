package cabanas.garcia.ismael.meetup.payment.infrastructure.configuration

import cabanas.garcia.ismael.meetup.payment.application.buysubscription.BuySubscriptionCommandHandler
import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommandHandler
import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.shared.infrastructure.service.DateProvider
import java.time.Instant
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentCommandHandlerConfiguration {
    @Bean
    fun createPayerCommandHandler(payerRepository: PayerRepository, eventBus: EventBus) =
        CreatePayerCommandHandler(payerRepository, eventBus)

    @Bean
    fun buySubscriptionCommandHandler(repository: SubscriptionPaymentRepository, eventBus: EventBus) =
        BuySubscriptionCommandHandler(repository, { Instant.now() }, eventBus)
}
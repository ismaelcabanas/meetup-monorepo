package cabanas.garcia.ismael.meetup.payment.infrastructure.configuration

import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.JpaPayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.JpaSubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaPayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaSubscriptionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentRepositoryConfiguration {
    @Bean
    fun jpaPayerRepository(
        springJpaPayerRepository: SpringJpaPayerRepository
    ): PayerRepository = JpaPayerRepository(springJpaPayerRepository)

    @Bean
    fun jpaSubscriptionPaymentRepository(
        springJpaSubscriptionRepository: SpringJpaSubscriptionRepository
    ): SubscriptionPaymentRepository = JpaSubscriptionPaymentRepository(springJpaSubscriptionRepository)
}
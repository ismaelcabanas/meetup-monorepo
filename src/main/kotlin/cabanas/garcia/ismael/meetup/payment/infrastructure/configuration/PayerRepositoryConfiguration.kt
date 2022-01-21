package cabanas.garcia.ismael.meetup.payment.infrastructure.configuration

import cabanas.garcia.ismael.meetup.payment.domain.PayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.JpaPayerRepository
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaPayerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PayerRepositoryConfiguration {
    @Bean
    fun jpaPayerRepository(
        springJpaPayerRepository: SpringJpaPayerRepository
    ): PayerRepository = JpaPayerRepository(springJpaPayerRepository)
}
package cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.Money
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentStatus
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.entities.JpaSubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.spring.SpringJpaSubscriptionRepository
import java.math.BigDecimal
import java.util.*

class JpaSubscriptionPaymentRepository(
    private val springJpaSubscriptionRepository: SpringJpaSubscriptionRepository
) : SubscriptionPaymentRepository {
    override fun get(id: SubscriptionPaymentId): SubscriptionPayment? =
        springJpaSubscriptionRepository.findById(UUID.fromString(id.value))
            .map { jpaToDomain(it) }
            .orElse(null)

    override fun add(subscriptionPayment: SubscriptionPayment) {
        springJpaSubscriptionRepository.save(subscriptionPayment.toJpa())
    }

    private fun jpaToDomain(jpaSubscriptionPayment: JpaSubscriptionPayment): SubscriptionPayment =
        SubscriptionPayment(
            SubscriptionPaymentId(jpaSubscriptionPayment.id.toString()),
            PayerId(jpaSubscriptionPayment.payerId.toString()),
            jpaSubscriptionPayment.status.toDomainStatus(),
            SubscriptionType.valueOf(jpaSubscriptionPayment.type),
            SubscriptionPeriod.valueOf(jpaSubscriptionPayment.period),
            Money(jpaSubscriptionPayment.moneyValue.toDouble()),
            jpaSubscriptionPayment.startDate
        )

    private fun String.toDomainStatus(): SubscriptionPaymentStatus = when (this) {
        WAITING_FOR_PAYMENT -> SubscriptionPaymentStatus.WAITING_FOR_PAYMENT
        else -> throw NotImplementedError("SubscriptionPayment status $this is not implemented.")
    }

    private fun SubscriptionPayment.toJpa(): JpaSubscriptionPayment =
        JpaSubscriptionPayment(
            UUID.fromString(this.paymentId.value),
            UUID.fromString(this.payerId.value),
            this.status.value,
            this.type.value,
            this.period.value,
            this.startDate,
            BigDecimal.valueOf(this.priceOffer.value)
        )

    private companion object {
        private const val WAITING_FOR_PAYMENT = "WAITING_FOR_PAYMENT"
    }
}

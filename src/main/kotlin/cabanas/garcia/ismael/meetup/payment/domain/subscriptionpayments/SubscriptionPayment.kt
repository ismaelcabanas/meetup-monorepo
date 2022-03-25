package cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event.SubscriptionPaymentCreated
import cabanas.garcia.ismael.meetup.shared.domain.AggregateRoot
import java.time.Instant

data class SubscriptionPayment(
    val paymentId: SubscriptionPaymentId,
    val payerId: PayerId,
    val status: SubscriptionPaymentStatus,
    val type: SubscriptionType,
    val period: SubscriptionPeriod,
    val priceOffer: Money,
    val startDate: Instant
) : AggregateRoot() {

    companion object {
        fun create(
            subscriptionPaymentId: SubscriptionPaymentId,
            payerId: PayerId,
            subscriptionType: SubscriptionType,
            subscriptionPeriod: SubscriptionPeriod,
            priceOffer: Money,
            startDate: Instant
        ): SubscriptionPayment {
            val subscriptionPayment = SubscriptionPayment(
                subscriptionPaymentId,
                payerId,
                SubscriptionPaymentStatus.WAITING_FOR_PAYMENT,
                subscriptionType,
                subscriptionPeriod,
                priceOffer,
                startDate
            )
            subscriptionPayment.registerDomainEvent(
                SubscriptionPaymentCreated(
                    subscriptionPayment.paymentId.value,
                    subscriptionPayment.payerId.value,
                    subscriptionPayment.status.value,
                    subscriptionPayment.type.value,
                    subscriptionPayment.period.value,
                    subscriptionPayment.priceOffer.value,
                    subscriptionPayment.startDate
                )
            )
            return subscriptionPayment
        }

    }
}
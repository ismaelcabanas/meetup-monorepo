package cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event.SubscriptionPaymentCreated
import cabanas.garcia.ismael.meetup.shared.domain.AggregateRoot

data class SubscriptionPayment(
    val paymentId: SubscriptionPaymentId
) : AggregateRoot() {
    companion object {
        fun create(
            subscriptionPaymentId: SubscriptionPaymentId,
            payerId: PayerId,
            subscriptionType: SubscriptionType,
            subscriptionPeriod: SubscriptionPeriod,
            priceOffer: Money
        ): SubscriptionPayment {
            val subscriptionPayment = SubscriptionPayment(
                subscriptionPaymentId
            )
            subscriptionPayment.registerDomainEvent(
                SubscriptionPaymentCreated(
                    subscriptionPaymentId.value,
                    payerId.value,
                    subscriptionType.value,
                    subscriptionPeriod.value,
                    priceOffer.value
                )
            )
            return subscriptionPayment
        }

    }
}
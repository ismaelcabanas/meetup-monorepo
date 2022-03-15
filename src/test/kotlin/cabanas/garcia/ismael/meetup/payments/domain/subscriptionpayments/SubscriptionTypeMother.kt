package cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType

object SubscriptionTypeMother {
    fun random(): SubscriptionType = SubscriptionType.valueOf(listOf("STANDARD", "PREMIUM").shuffled().first())
}
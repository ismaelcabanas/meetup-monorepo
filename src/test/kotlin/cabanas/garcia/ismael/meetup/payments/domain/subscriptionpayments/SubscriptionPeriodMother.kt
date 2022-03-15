package cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod

object SubscriptionPeriodMother {
    fun random(): SubscriptionPeriod = SubscriptionPeriod.valueOf(listOf("MONTHLY", "ANNUAL").shuffled().first())
}
package cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class SubscriptionPaymentCreated(
    val paymentId: String,
    val payerId: String,
    val type: String,
    val period: String,
    val priceOffer: Double
) : DomainEvent

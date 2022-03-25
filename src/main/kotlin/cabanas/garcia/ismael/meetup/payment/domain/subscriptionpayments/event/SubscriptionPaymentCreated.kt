package cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.event

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class SubscriptionPaymentCreated(
    val paymentId: String,
    val payerId: String,
    val status: String,
    val type: String,
    val period: String,
    val priceOffer: Double,
    val startDate: Instant
) : DomainEvent

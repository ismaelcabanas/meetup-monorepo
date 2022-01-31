package cabanas.garcia.ismael.meetup.payment.api.v1

import java.time.Instant

class PostSubscriptionController {
}

data class BuySubscriptionRequest(
    val paymentId: String,
    val type: String,
    val value: Double,
    val period: String,
    val date: Instant
)
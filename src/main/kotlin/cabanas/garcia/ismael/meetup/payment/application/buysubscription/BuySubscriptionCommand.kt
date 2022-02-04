package cabanas.garcia.ismael.meetup.payment.application.buysubscription

import cabanas.garcia.ismael.meetup.shared.application.Command
import java.time.Instant

data class BuySubscriptionCommand(
    val paymentId: String,
    val payerId: String,
    val type: String,
    val value: Double,
    val period: String,
    val date: Instant
) : Command

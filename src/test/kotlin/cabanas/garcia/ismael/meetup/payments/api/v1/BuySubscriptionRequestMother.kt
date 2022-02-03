package cabanas.garcia.ismael.meetup.payments.api.v1

import cabanas.garcia.ismael.meetup.payment.api.v1.BuySubscriptionRequest
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.time.Instant

object BuySubscriptionRequestMother {
    fun random(
        paymentId: String? = MotherCreator.faker().internet().uuid(),
        type: String? = listOf("STANDARD", "PREMIUM").asSequence().shuffled().take(1).first(),
        value: Double? = MotherCreator.faker().number().randomDouble(2, 1, 100),
        period: String? = listOf("MONTH", "ANNUAL").asSequence().shuffled().take(1).first(),
        date: Instant? = Instant.now()
    ): BuySubscriptionRequest =
        BuySubscriptionRequest(
            paymentId = paymentId,
            type = type,
            value = value,
            period = period,
            date = date
        )
}
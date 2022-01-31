package cabanas.garcia.ismael.meetup.payments.api.v1

import cabanas.garcia.ismael.meetup.payment.api.v1.BuySubscriptionRequest
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.time.Instant

object BuySubscriptionRequestMother {
    fun randomStandardMonth(): BuySubscriptionRequest =
        BuySubscriptionRequest(
            paymentId = MotherCreator.faker().internet().uuid(),
            type = "STANDARD",
            value = MotherCreator.faker().number().randomDouble(2, 1, 100),
            period = "MONTH",
            date = Instant.now()
        )
}
package cabanas.garcia.ismael.meetup.payments.application.buysubscription

import cabanas.garcia.ismael.meetup.payment.application.buysubscription.BuySubscriptionCommand
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.time.Instant

object BuySubscriptionCommandMother {
    fun random(
        paymentId: String? = MotherCreator.faker().internet().uuid(),
        payerId: String? = MotherCreator.faker().internet().uuid(),
        type: String? = listOf("STANDARD", "PREMIUM").asSequence().shuffled().take(1).first(),
        value: Double? = MotherCreator.faker().number().randomDouble(2, 1, 100),
        period: String? = listOf("MONTH", "ANNUAL").asSequence().shuffled().take(1).first(),
        date: Instant? = Instant.now()
    ): BuySubscriptionCommand =
        BuySubscriptionCommand(
            paymentId = paymentId!!,
            payerId = payerId!!,
            type = type!!,
            value = value!!,
            period = period!!,
            date = date!!
        )
}
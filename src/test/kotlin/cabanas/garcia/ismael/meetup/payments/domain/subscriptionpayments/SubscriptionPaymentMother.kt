package cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.Money
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.time.Instant

object SubscriptionPaymentMother {
    fun waitingForPayment(
        id: String? = MotherCreator.faker().internet().uuid(),
        payerId: String? = MotherCreator.faker().internet().uuid(),
        type: SubscriptionType? = SubscriptionTypeMother.random(),
        period: SubscriptionPeriod? = SubscriptionPeriodMother.random(),
        price: Double? = MoneyMother.random().value,
        startDate: Instant? = Instant.now()
    ): SubscriptionPayment = SubscriptionPayment.create(
        subscriptionPaymentId = SubscriptionPaymentId(id!!),
        payerId = PayerId(payerId!!),
        subscriptionType = type!!,
        subscriptionPeriod = period!!,
        priceOffer = Money(price!!),
        startDate = startDate!!
    )
}
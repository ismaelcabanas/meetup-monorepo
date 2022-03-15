package cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.Money
import cabanas.garcia.ismael.meetup.shared.MotherCreator

object MoneyMother {
    fun random(): Money = Money(MotherCreator.faker().number().randomDouble(2, 1, 100))
}
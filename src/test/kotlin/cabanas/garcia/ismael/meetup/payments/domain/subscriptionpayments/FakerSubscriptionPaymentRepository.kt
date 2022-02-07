package cabanas.garcia.ismael.meetup.payments.domain.subscriptionpayments

import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository

class FakerSubscriptionPaymentRepository : SubscriptionPaymentRepository {
    private val database = HashMap<SubscriptionPaymentId, SubscriptionPayment>()

    override fun get(id: SubscriptionPaymentId): SubscriptionPayment =
        database[id]
            ?: throw IllegalArgumentException("SubscriptionPayment not found: Id: $id")

    override fun add(subscriptionPayment: SubscriptionPayment) {
        database[subscriptionPayment.paymentId] = subscriptionPayment
    }
}
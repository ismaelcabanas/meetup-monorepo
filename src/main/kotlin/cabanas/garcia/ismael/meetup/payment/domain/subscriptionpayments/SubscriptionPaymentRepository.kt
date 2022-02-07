package cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments

interface SubscriptionPaymentRepository {
    fun get(id: SubscriptionPaymentId): SubscriptionPayment
    fun add(subscriptionPayment: SubscriptionPayment)
}
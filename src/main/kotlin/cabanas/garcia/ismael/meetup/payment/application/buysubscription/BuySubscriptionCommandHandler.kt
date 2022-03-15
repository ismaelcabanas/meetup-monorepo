package cabanas.garcia.ismael.meetup.payment.application.buysubscription

import cabanas.garcia.ismael.meetup.payment.domain.PayerId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.Money
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPayment
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentId
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPaymentRepository
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionPeriod
import cabanas.garcia.ismael.meetup.payment.domain.subscriptionpayments.SubscriptionType
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.shared.infrastructure.service.DateProvider

class BuySubscriptionCommandHandler(
    private val repository: SubscriptionPaymentRepository,
    private val dateProvider: DateProvider,
    private val eventBus: EventBus
) : CommandHandler<BuySubscriptionCommand> {
    override fun handle(command: BuySubscriptionCommand) {
        SubscriptionPayment.create(
            SubscriptionPaymentId(command.paymentId),
            PayerId(command.payerId),
            SubscriptionType.valueOf(command.type),
            SubscriptionPeriod.valueOf(command.period),
            Money(command.value),
            dateProvider.now()
        ).let {
            repository.add(it)
                .apply {
                    eventBus.publish(it.pullEvents()) }
        }
    }
}
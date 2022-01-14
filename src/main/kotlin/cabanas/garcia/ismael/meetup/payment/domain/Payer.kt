package cabanas.garcia.ismael.meetup.payment.domain

import cabanas.garcia.ismael.meetup.payment.domain.event.PayerCreated
import cabanas.garcia.ismael.meetup.shared.domain.AggregateRoot

data class Payer(val id: PayerId, val email: String, val firstName: String, val lastName: String) : AggregateRoot() {
    companion object {
        fun create(payerId: PayerId, email: String, firstName: String, lastName: String) =
            Payer(payerId, email, firstName, lastName)
                .also {
                    it.registerDomainEvent(
                        PayerCreated(it.id.value, it.email, it.firstName, it.lastName)
                    )
                }
    }
}

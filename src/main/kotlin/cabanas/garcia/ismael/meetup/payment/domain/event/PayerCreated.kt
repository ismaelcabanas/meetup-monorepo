package cabanas.garcia.ismael.meetup.payment.domain.event

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class PayerCreated(
    val userId: String,
    val firstName: String,
    val lastName: String
) : DomainEvent

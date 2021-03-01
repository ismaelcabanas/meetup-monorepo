package cabanas.garcia.ismael.meetup.domain.shared.service

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

interface EventBus {
    fun publish(domainEvents: List<DomainEvent>)
}
package cabanas.garcia.ismael.meetup.shared.domain.service

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

interface EventBus {
    fun publish(domainEvents: List<DomainEvent>)
}
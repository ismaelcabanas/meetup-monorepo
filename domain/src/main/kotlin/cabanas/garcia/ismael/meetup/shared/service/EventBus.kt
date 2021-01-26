package cabanas.garcia.ismael.meetup.shared.service

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

interface EventBus {
    fun publish(domainEvents: List<DomainEvent>)
}
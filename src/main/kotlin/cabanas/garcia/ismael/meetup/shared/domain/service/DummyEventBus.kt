package cabanas.garcia.ismael.meetup.shared.domain.service

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

class DummyEventBus : EventBus {
    override fun publish(domainEvents: List<DomainEvent>) {

    }
}
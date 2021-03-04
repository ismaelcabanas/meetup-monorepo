package cabanas.garcia.ismael.meetup.shared.domain

abstract class AggregateRoot: Entity() {
    protected var events = mutableListOf<DomainEvent>()

    fun events() = events

    fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
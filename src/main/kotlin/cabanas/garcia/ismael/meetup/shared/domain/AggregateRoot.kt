package cabanas.garcia.ismael.meetup.shared.domain

abstract class AggregateRoot: Entity() {
    protected var events = mutableListOf<DomainEvent>()

    fun events() = events.toList()

    fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
package cabanas.garcia.ismael.meetup.shared.domain

abstract class AggregateRoot: Entity() {
    protected var events = mutableListOf<DomainEvent>()

    fun pullEvents(): List<DomainEvent> {
        val eventsToPull = events.toList()
        events = emptyList<DomainEvent>().toMutableList()
        return eventsToPull
    }

    fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
package cabanas.garcia.ismael.meetup.shared.infrastructure.eventbus.spring

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import org.springframework.context.ApplicationEventPublisher

class SpringEventBus(
    private val publisher: ApplicationEventPublisher
) : EventBus {
    override fun publish(domainEvents: List<DomainEvent>) {
        domainEvents.forEach { domainEvent ->
            publisher.publishEvent(domainEvent)
        }
    }
}
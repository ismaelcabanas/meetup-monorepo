package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposalCreated(val userId: String, val country: String, val city: String, val date: Instant)
    : DomainEvent {
}
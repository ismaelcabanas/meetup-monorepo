package cabanas.garcia.ismael.meetup.administration.domain

import cabanas.garcia.ismael.meetup.shared.DomainEvent
import java.time.Instant

data class MeetupGroupProposalCreated(
    val meetupGroupProposalId: String,
    val proposalId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val proposalDate: Instant
) : DomainEvent
package cabanas.garcia.ismael.meetup.administration.domain

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class MeetupGroupProposalRejected(
    val meetupGroupProposalId: String,
    val proposalUserId: String,
    val reason: String,
    val decisionDate: Instant
) : DomainEvent

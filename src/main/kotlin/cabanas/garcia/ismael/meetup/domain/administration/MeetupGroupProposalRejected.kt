package cabanas.garcia.ismael.meetup.domain.administration

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetupGroupProposalRejected(
    val meetupGroupProposalId: String,
    val proposalUserId: String,
    val reason: String,
    val decisionDate: Instant
) : DomainEvent
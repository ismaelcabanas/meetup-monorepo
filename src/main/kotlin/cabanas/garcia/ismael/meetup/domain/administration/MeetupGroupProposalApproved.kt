package cabanas.garcia.ismael.meetup.domain.administration

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetupGroupProposalApproved(
    val meetupGroupProposalId: String,
    val proposalId: String,
    val approveDate: Instant
) : DomainEvent
package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposalApproved(
    val meetupGroupProposalId: String,
    val proposalId: String,
    val approveDate: Instant
) : DomainEvent
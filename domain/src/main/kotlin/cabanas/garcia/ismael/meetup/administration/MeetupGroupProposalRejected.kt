package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposalRejected(
    val meetupGroupProposalId: String,
    val proposalUserId: String,
    val reason: String,
    val decisionDate: Instant
) : DomainEvent

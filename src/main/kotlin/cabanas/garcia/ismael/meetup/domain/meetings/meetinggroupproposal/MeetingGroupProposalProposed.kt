package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetingGroupProposalProposed(
    val meetingGroupProposalId: String,
    val proposalMemberId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val proposalDate: Instant
) : DomainEvent

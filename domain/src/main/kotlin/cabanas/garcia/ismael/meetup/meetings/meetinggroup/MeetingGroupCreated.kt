package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingGroupCreated(
    val meetingGroupProposalId: String,
    val creatorId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val creationDate: Instant
) : DomainEvent

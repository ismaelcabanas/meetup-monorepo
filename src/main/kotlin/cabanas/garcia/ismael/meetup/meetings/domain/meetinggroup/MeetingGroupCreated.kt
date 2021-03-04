package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class MeetingGroupCreated(
    val meetingGroupId: String,
    val creatorId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val creationDate: Instant
) : DomainEvent

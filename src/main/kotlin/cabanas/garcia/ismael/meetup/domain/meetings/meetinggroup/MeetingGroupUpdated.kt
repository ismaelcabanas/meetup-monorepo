package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

data class MeetingGroupUpdated(
    val meetingGroupId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String
) : DomainEvent

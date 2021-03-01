package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.shared.DomainEvent

data class MeetingGroupUpdated(
    val meetingGroupId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String
) : DomainEvent


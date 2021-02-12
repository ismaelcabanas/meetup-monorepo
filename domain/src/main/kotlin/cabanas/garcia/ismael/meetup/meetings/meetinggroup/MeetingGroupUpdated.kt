package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetingGroupUpdated(
    val meetingGroupId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String
) : DomainEvent


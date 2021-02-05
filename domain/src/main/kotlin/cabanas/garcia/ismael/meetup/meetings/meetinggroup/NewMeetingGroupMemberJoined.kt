package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class NewMeetingGroupMemberJoined(
    val meetingGroupId: String,
    val memberId: String
) : DomainEvent

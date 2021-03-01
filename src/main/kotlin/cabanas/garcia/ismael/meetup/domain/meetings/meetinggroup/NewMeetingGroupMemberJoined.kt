package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

data class NewMeetingGroupMemberJoined(
    val meetingGroupId: String,
    val memberId: String
) : DomainEvent

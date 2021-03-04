package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class MeetingGroupMemberLeftGroup(
    val meetingGroupId: String,
    val memberId: String
) : DomainEvent

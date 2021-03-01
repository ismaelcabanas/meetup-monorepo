package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class MemberMeetingGroup(
    val meetingGroupId: MeetingGroupId,
    val memberId: MemberId
)

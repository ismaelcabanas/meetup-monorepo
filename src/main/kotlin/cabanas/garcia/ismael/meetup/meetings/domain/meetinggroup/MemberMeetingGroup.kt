package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class MemberMeetingGroup(
    val meetingGroupId: MeetingGroupId,
    val memberId: MemberId
)

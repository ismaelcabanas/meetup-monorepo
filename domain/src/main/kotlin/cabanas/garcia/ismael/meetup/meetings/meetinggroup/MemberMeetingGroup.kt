package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.member.MemberId

data class MemberMeetingGroup(
    val meetingGroupId: MeetingGroupId,
    val memberId: MemberId
)

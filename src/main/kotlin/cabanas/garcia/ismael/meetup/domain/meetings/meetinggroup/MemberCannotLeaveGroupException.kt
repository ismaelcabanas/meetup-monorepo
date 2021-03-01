package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class MemberCannotLeaveGroupException(val memberId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception("Member '${memberId.value}' cannot leave the group '${meetingGroupId.value}'.") {
}
package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class MemberCannotLeaveGroupException(val memberId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception("Member '${memberId.value}' cannot leave the group '${meetingGroupId.value}'.") {
}
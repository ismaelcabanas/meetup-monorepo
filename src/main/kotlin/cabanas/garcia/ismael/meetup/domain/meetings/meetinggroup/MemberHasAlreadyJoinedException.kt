package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class MemberHasAlreadyJoinedException(
    val memberId: MemberId,
    val meetingGroupId: MeetingGroupId
    ) : Exception("Member '${memberId.value}' has already joined to group '${meetingGroupId.value}'") {
}
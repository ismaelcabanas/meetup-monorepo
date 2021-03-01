package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class MemberHasAlreadyJoinedException(
    val memberId: MemberId,
    val meetingGroupId: MeetingGroupId
    ) : Exception("Member '${memberId.value}' has already joined to group '${meetingGroupId.value}'") {
}
package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

data class CreatorMemberCannotLeaveGroupException(val creatorId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception("Creator member '${creatorId.value}' cannot leave the group '${meetingGroupId.value}'.") {
}
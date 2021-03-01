package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class CreatorMemberCannotLeaveGroupException(val creatorId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception("Creator member '${creatorId.value}' cannot leave the group '${meetingGroupId.value}'.") {
}
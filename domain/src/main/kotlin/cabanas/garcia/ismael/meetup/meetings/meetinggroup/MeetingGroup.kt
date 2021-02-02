package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.member.MemberId

data class MeetingGroup(
    val id: MeetingGroupId,
    val membersMeetingGroup: List<MemberMeetingGroup>
) {
    fun isMemberMeetingGroup(memberId: MemberId): Boolean {
        val memberMeetingGroup = membersMeetingGroup.stream()
            .filter { memberMeetingGroup -> memberMeetingGroup.memberId == memberId }
            .findFirst()

        return memberMeetingGroup.isPresent
    }
}

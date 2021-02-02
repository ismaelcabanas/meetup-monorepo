package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.member.MemberId

object MeetingGroupMother {
    private const val SOME_MEETING_GROUP_ID = "some meeting group id"

    fun withMember(memberId: String): MeetingGroup =
        MeetingGroup(
            MeetingGroupId(SOME_MEETING_GROUP_ID),
            listOf(MemberMeetingGroup(MeetingGroupId(SOME_MEETING_GROUP_ID), MemberId(memberId)))
        )
}
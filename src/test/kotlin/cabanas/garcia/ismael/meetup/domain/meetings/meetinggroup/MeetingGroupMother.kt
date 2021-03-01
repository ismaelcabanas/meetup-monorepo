package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
import java.time.Instant

object MeetingGroupMother {
    private const val SOME_MEETING_GROUP_ID = "some meeting group id"
    private const val SOME_NAME = "some name"
    private const val SOME_DESCRIPTION = "some description"
    private const val SOME_COUNTRY = "some country"
    private const val SOME_CITY = "some city"
    private const val SOME_CREATION_DATE = "2021-02-01T03:13:03Z"
    private const val SOME_CREATOR_MEMBER_ID = "some creator member id"

    fun withMember(memberId: String): MeetingGroup =
        create().join(MemberId(memberId))

    fun create(): MeetingGroup =
        MeetingGroup(
            MeetingGroupId(SOME_MEETING_GROUP_ID),
            MemberId(SOME_CREATOR_MEMBER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_CREATION_DATE)
        )

    fun withMembers(attendees: List<MemberId>?): MeetingGroup {
        var meetingGroup = create()

        attendees?.forEach { meetingGroup = meetingGroup.join(it) }

        return meetingGroup
    }
}
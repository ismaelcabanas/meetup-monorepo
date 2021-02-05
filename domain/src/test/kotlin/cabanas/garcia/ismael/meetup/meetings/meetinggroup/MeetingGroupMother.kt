package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import java.time.Instant

object MeetingGroupMother {
    private const val SOME_MEETING_GROUP_ID = "some meeting group id"
    private const val SOME_NAME = "some name"
    private const val SOME_DESCRIPTION = "some description"
    private const val SOME_COUNTRY = "some country"
    private const val SOME_CITY = "some city"
    private const val SOME_CREATION_DATE = "2021-02-01T03:13:03Z"

    fun withMember(memberId: String): MeetingGroup =
        MeetingGroup(
            MeetingGroupId(SOME_MEETING_GROUP_ID),
            MemberId(memberId),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_CREATION_DATE)
        )
}
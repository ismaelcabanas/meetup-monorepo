package cabanas.garcia.ismael.meetup.meetings.meeting

import java.time.Instant

object MeetingMother {
    private const val SOME_MEETING_ID = "some meeting id"
    private const val SOME_START_DATE = "2021-01-20T11:11:01Z"
    private const val SOME_END_DATE = "2021-01-20T21:11:01Z"

    fun create(meetingTerm: MeetingTerm?): Meeting =
        Meeting(
            MeetingId(SOME_MEETING_ID),
            meetingTerm ?: MeetingTerm(Instant.parse(SOME_START_DATE), Instant.parse(SOME_END_DATE))
        )
}

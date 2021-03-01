package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
import java.time.Instant

object MeetingMother {
    private const val SOME_MEETING_ID = "some meeting id"
    private const val SOME_MEETING_START_DATE = "2021-01-21T11:00:00Z"
    private const val SOME_MEETING_END_DATE = "2021-01-21T21:00:00Z"
    private const val SOME_ENROLMENT_START_DATE = "2021-01-01T00:00:00Z"
    private const val SOME_ENROLMENT_END_DATE = "2021-01-20T00:00:00Z"
    private const val ENROLMENT_DATE = "2021-01-15T00:00:00Z"

    fun create(
        meetingGroup: MeetingGroup? = null,
        meetingAttendeeLimits: MeetingAttendeeLimits? = null,
        enrolmentTerm: EnrolmentTerm? = null,
        meetingTerm: MeetingTerm? = null,
        attendees: List<MemberId>? = null
    ): Meeting {
        var meeting = Meeting.create(
            MeetingId(SOME_MEETING_ID),
            meetingGroup ?: MeetingGroupMother.withMembers(attendees?.map { it }),
            meetingAttendeeLimits ?: MeetingAttendeeLimits(5, 2),
            enrolmentTerm ?: EnrolmentTerm(Instant.parse(SOME_ENROLMENT_START_DATE), Instant.parse(SOME_ENROLMENT_END_DATE)),
            meetingTerm ?: MeetingTerm(Instant.parse(SOME_MEETING_START_DATE), Instant.parse(SOME_MEETING_END_DATE))
        )

        attendees?.let { it ->
            it.stream().forEach {
                meeting = meeting.addAttendee(it, Instant.parse(ENROLMENT_DATE))
            }
        }

        return meeting
    }

}

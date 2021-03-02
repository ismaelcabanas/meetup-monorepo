package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import java.time.Instant
import java.time.Period

object MeetingMother {
    private const val SOME_MEETING_ID = "some meeting id"
    private const val SOME_MEETING_START_DATE = "2021-01-21T11:00:00Z"
    private const val SOME_MEETING_END_DATE = "2021-01-21T21:00:00Z"
    private const val SOME_ENROLMENT_START_DATE = "2021-01-01T00:00:00Z"
    private const val SOME_ENROLMENT_END_DATE = "2021-01-20T00:00:00Z"
    private const val ENROLMENT_DATE = "2021-01-15T00:00:00Z"

    fun create(
        meetingId: String? = null,
        meetingGroup: MeetingGroup? = null,
        meetingAttendeeLimits: MeetingAttendeeLimits? = null,
        enrolmentTerm: EnrolmentTerm? = null,
        meetingTerm: MeetingTerm? = null,
        attendees: List<MemberId>? = null
    ): Meeting {
        var meeting = Meeting.create(
            MeetingId(meetingId ?: SOME_MEETING_ID),
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

    fun notStartedYet(meetingId: String?) =
        create(
            meetingId,
            enrolmentTerm = EnrolmentTerm(
                Instant.now().minus(Period.ofDays(5)),
                Instant.now().plus(Period.ofDays(1))
            ),
            meetingTerm = MeetingTerm(
                Instant.now().plus(Period.ofDays(3)),
                Instant.now().plus(Period.ofDays(4))
            )
        )

    fun started() =
        create(
            meetingTerm = MeetingTerm(
                Instant.now().minus(Period.ofDays(1)),
                Instant.now().plus(Period.ofDays(1))
            )
        )

    fun outOfEnrolmentTerm() =
        create(
            enrolmentTerm = EnrolmentTerm(
                Instant.now().minus(Period.ofDays(5)),
                Instant.now().minus(Period.ofDays(1))
            ),
            meetingTerm = MeetingTerm(
                Instant.now().plus(Period.ofDays(1)),
                Instant.now().plus(Period.ofDays(2))
            )
        )

}

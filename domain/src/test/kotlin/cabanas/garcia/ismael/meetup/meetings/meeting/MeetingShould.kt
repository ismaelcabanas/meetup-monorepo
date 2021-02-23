package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import java.time.Instant
import org.junit.jupiter.api.Test

class MeetingShould {
    @Test
    fun `cancel successfully when has not started`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            )
        )
        val cancelMemberId = MemberId(SOME_MEMBER_ID)
        val cancelDate = Instant.parse("2021-01-21T12:11:01Z")

        val meetingCanceled = meeting.cancel(cancelMemberId, cancelDate)

        meetingCanceled.events() shouldContain
                MeetingCanceled(
                    meetingCanceled.id.value,
                    SOME_MEMBER_ID,
                    cancelDate
                )
        meetingCanceled.cancelMemberId shouldBe cancelMemberId
        meetingCanceled.cancelDate shouldBe cancelDate
    }

    @Test
    fun `fail when cancel meeting after has started`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            )
        )
        val cancelMemberId = MemberId(SOME_MEMBER_ID)
        val cancelDate = Instant.parse("2021-01-21T10:11:01Z")

        val exception = shouldThrow<MeetingCannotChangedAfterHasStartedException> {
            meeting.cancel(cancelMemberId, cancelDate)
        }

        exception.message shouldBe "Meeting ${meeting.id.value} cannot be changed after it has started."
    }

    @Test
    fun `fail when remove a meeting attendee after meeting has started`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            )
        )
        val removingDate = Instant.parse("2021-01-21T13:11:01Z")

        val exception = shouldThrow<MeetingCannotChangedAfterHasStartedException> {
            meeting.removeAttendee(
                MemberId(SOME_MEMBER_ID),
                MemberId(ANOTHER_MEMBER_ID),
                removingDate
            )
        }

        exception.message shouldBe "Meeting ${meeting.id.value} cannot be changed after it has started."
    }

    @Test
    fun `fail when remove a member that is not a meeting attendee`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            )
        )
        val removingDate = Instant.parse("2021-01-21T09:11:01Z")

        val exception = shouldThrow<OnlyMeetingAttendeesCanBeRemovedException> {
            meeting.removeAttendee(
                MemberId(SOME_MEMBER_ID),
                MemberId(ANOTHER_MEMBER_ID),
                removingDate
            )
        }

        exception.message shouldBe "Member '$SOME_MEMBER_ID' is not an attendee of meeting '${meeting.id.value}'."
    }

    @Test
    fun `remove meeting attendee with reason successfully`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            ),
            attendees = listOf(MeetingAttendee(MemberId(SOME_MEMBER_ID)))
        )
        val removingDate = Instant.parse("2021-01-21T09:11:01Z")

        val meetingWithAttendeeRemoved = meeting.removeAttendee(
            MemberId(SOME_MEMBER_ID),
            MemberId(ANOTHER_MEMBER_ID),
            removingDate,
            SOME_REASON
        )

        meetingWithAttendeeRemoved.events() shouldContain
                MeetingAttendeeRemoved(
                    meetingWithAttendeeRemoved.id.value,
                    SOME_MEMBER_ID,
                    ANOTHER_MEMBER_ID,
                    removingDate,
                    SOME_REASON
                )
        meeting.attendees() shouldContain
                MeetingAttendee(
                    MemberId(SOME_MEMBER_ID),
                    removingPersonId = MemberId(ANOTHER_MEMBER_ID),
                    removingDate = removingDate,
                    removingReason = SOME_REASON
                )
    }

    @Test
    fun `fail when remove meeting attendee without reason provided`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            ),
            attendees = listOf(MeetingAttendee(MemberId(SOME_MEMBER_ID)))
        )
        val removingDate = Instant.parse("2021-01-21T09:11:01Z")

        val exception = shouldThrow<ReasonOfRemovingAttendeeFromMeetingMustBeProvidedException> {
            meeting.removeAttendee(
                MemberId(SOME_MEMBER_ID),
                MemberId(ANOTHER_MEMBER_ID),
                removingDate
            )
        }

        exception.message shouldBe
                "Reason of removing attendee '$SOME_MEMBER_ID' from meeting '${meeting.id.value}' must be provided."
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
        private const val ANOTHER_MEMBER_ID = "another member id"
        private const val SOME_REASON = "some reason of removal"
    }
}
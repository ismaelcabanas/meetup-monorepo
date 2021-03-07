package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingAttendeeAdded
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingAttendeeRemoved
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingCanceled
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.DomainException
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
        val cancelDate = Instant.parse("2021-01-13T12:11:01Z")

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
        val cancelDate = Instant.parse("2021-01-21T15:11:01Z")

        val exception = shouldThrow<DomainException> {
            meeting.cancel(cancelMemberId, cancelDate)
        }

        exception.message shouldBe "Meeting cannot be changed after start."
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

        val exception = shouldThrow<DomainException> {
            meeting.removeAttendee(
                MemberId(SOME_MEMBER_ID),
                MemberId(ANOTHER_MEMBER_ID),
                removingDate
            )
        }

        exception.message shouldBe "Meeting cannot be changed after start."
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
            attendees = listOf(MemberId(SOME_MEMBER_ID))
        )
        val removingDate = Instant.parse("2021-01-21T09:11:01Z")

        val meetingWithAttendeeRemoved = meeting.removeAttendee(
            MemberId(SOME_MEMBER_ID),
            MemberId(REMOVER_MEMBER_ID),
            removingDate,
            SOME_REASON
        )

        meetingWithAttendeeRemoved.events() shouldContain
                MeetingAttendeeRemoved(
                    meetingWithAttendeeRemoved.id.value,
                    SOME_MEMBER_ID,
                    REMOVER_MEMBER_ID,
                    removingDate,
                    SOME_REASON
                )
        val attendeeRemoved = meetingWithAttendeeRemoved.attendees().find { it.id == MemberId(SOME_MEMBER_ID) }!!
        attendeeRemoved.removingPersonId shouldBe MemberId(REMOVER_MEMBER_ID)
        attendeeRemoved.removingDate shouldBe removingDate
        attendeeRemoved.removingReason shouldBe SOME_REASON
    }

    @Test
    fun `fail when remove meeting attendee without reason provided`() {
        val meeting = MeetingMother.create(
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-21T11:11:01Z"),
                Instant.parse("2021-01-21T21:11:01Z")
            ),
            attendees = listOf(MemberId(SOME_MEMBER_ID))
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

    @Test
    fun `add meeting attendee successfully`() {
        val meeting = MeetingMother.create(
            meetingGroup = MeetingGroupMother.withMember(SOME_MEMBER_ID),
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            )
        )
        val newAttendeeId = MemberId(SOME_MEMBER_ID)
        val enrolmentDate = Instant.parse("2021-01-15T00:00:00Z")

        val meetingWithNewAttendee = meeting.addAttendee(newAttendeeId, enrolmentDate)

        meetingWithNewAttendee.events() shouldContain
                MeetingAttendeeAdded(
                    newAttendeeId.value,
                    meeting.id.value,
                    enrolmentDate
                )
        meetingWithNewAttendee.attendees() shouldContain
                MeetingAttendee(newAttendeeId, meeting.id, enrolmentDate)
    }

    @Test
    fun `fail when add meeting attendee before of enrolment term to meeting`() {
        val meeting = MeetingMother.create(
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            )
        )
        val newAttendeeId = MemberId(SOME_MEMBER_ID)
        val enrolmentDate = Instant.parse("2020-12-31T00:00:00Z")

        val exception = shouldThrow<DomainException> {
            meeting.addAttendee(newAttendeeId, enrolmentDate)
        }

        exception.message shouldBe "Attendee can be added only in enrolment term."
    }

    @Test
    fun `fail when add meeting attendee after of enrolment term to meeting`() {
        val meeting = MeetingMother.create(
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            ),
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-23T10:00:00Z"),
                Instant.parse("2021-01-23T15:00:00Z")
            )
        )
        val newAttendeeId = MemberId(SOME_MEMBER_ID)
        val enrolmentDate = Instant.parse("2021-01-22T00:00:00Z")

        val exception = shouldThrow<DomainException> {
            meeting.addAttendee(newAttendeeId, enrolmentDate)
        }

        exception.message shouldBe "Attendee can be added only in enrolment term."
    }
    
    @Test
    fun `fail when add meeting attendee that does not belong to meetup group`() {
        val meeting = MeetingMother.create(
            meetingGroup = MeetingGroupMother.withMember(SOME_MEMBER_ID),
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            )
        )
        val newAttendeeId = MemberId(NO_MEMBER_MEETING_GROUP_ID)
        val enrolmentDate = Instant.parse("2021-01-15T00:00:00Z")

        val exception = shouldThrow<MeetingAttendeeMustBeAMemberOfMeetingGroupException> {
            meeting.addAttendee(newAttendeeId, enrolmentDate)
        }

        exception.message shouldBe "Meeting attendee must be a member of group."
    }
    
    @Test
    fun `fail when add meeting attendee with number of guests above of meeting limit guests`() {
        val meeting = MeetingMother.create(
            meetingGroup = MeetingGroupMother.withMember(SOME_MEMBER_ID),
            meetingAttendeeLimits = MeetingAttendeeLimits(guestAttendeeLimit = 2),
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            )
        )
        val newAttendeeId = MemberId(SOME_MEMBER_ID)
        val enrolmentDate = Instant.parse("2021-01-15T00:00:00Z")

        val exception = shouldThrow<MeetingGuestsNumberIsAboveLimitException> {
            meeting.addAttendee(newAttendeeId, enrolmentDate, 3)
        }

        exception.message shouldBe "Meeting guests number is above limit."
    }

    @Test
    fun `fail when add meeting attendee above of meeting limit attendees`() {
        val meeting = MeetingMother.create(
            meetingGroup = MeetingGroupMother.withMembers(listOf(MemberId(SOME_MEMBER_ID), MemberId(ANOTHER_MEMBER_ID))),
            meetingAttendeeLimits = MeetingAttendeeLimits(3, 2),
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            )
        )
        val enrolmentDate = Instant.parse("2021-01-15T00:00:00Z")
        val meetingWithOneAttendee = meeting.addAttendee(MemberId(SOME_MEMBER_ID), enrolmentDate, 1)

        val exception = shouldThrow<MeetingAttendeesNumberIsAboveLimitException> {
            meetingWithOneAttendee.addAttendee(MemberId(ANOTHER_MEMBER_ID), enrolmentDate, 1)
        }

        exception.message shouldBe "Meeting attendees number is above limit."
    }

    @Test
    fun `fail when add meeting attendee and meeting has started`() {
        val meeting = MeetingMother.create(
            meetingGroup = MeetingGroupMother.withMembers(listOf(MemberId(SOME_MEMBER_ID), MemberId(ANOTHER_MEMBER_ID))),
            meetingAttendeeLimits = MeetingAttendeeLimits(3, 2),
            enrolmentTerm = EnrolmentTerm(
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-21T00:00:00Z")
            ),
            meetingTerm = MeetingTerm(
                Instant.parse("2021-01-22T10:00:00Z"),
                Instant.parse("2021-01-22T15:00:00Z")
            )
        )
        val enrolmentDate = Instant.parse("2021-01-22T13:00:00Z")

        val exception = shouldThrow<DomainException> {
            meeting.addAttendee(MemberId(ANOTHER_MEMBER_ID), enrolmentDate, 1)
        }

        exception.message shouldBe "Meeting cannot be changed after start."
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
        private const val ANOTHER_MEMBER_ID = "another member id"
        private const val REMOVER_MEMBER_ID = "remover member id"
        private const val NO_MEMBER_MEETING_GROUP_ID = "another member id"
        private const val SOME_REASON = "some reason of removal"
    }
}
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
            startDate = Instant.parse("2021-01-21T11:11:01Z"),
            endDate = Instant.parse("2021-01-21T21:11:01Z")
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
            startDate = Instant.parse("2021-01-21T11:11:01Z"),
            endDate = Instant.parse("2021-01-21T21:11:01Z")
        )
        val cancelMemberId = MemberId(SOME_MEMBER_ID)
        val cancelDate = Instant.parse("2021-01-21T10:11:01Z")

        val exception = shouldThrow<MeetingCannotCanceledAfterStartException> {
            meeting.cancel(cancelMemberId, cancelDate)
        }

        exception.message shouldBe "Meeting ${meeting.id.value} cannot canceled because it has started."
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
    }
}
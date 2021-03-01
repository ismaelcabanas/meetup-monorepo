package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingAttendeeLimitsShould {
    @Test
    fun `create meeting limits successfully when member attendees limits is greater than guests attendees limits`() {
        val meetingLimits = MeetingAttendeeLimits(10, 5)

        meetingLimits.memberAttendeeLimit shouldBe 10
        meetingLimits.guestAttendeeLimit shouldBe 5
    }

    @Test
    fun `create meeting limits successfully when member attendees limits is equal to guests attendees limits`() {
        val meetingLimits = MeetingAttendeeLimits(10, 10)

        meetingLimits.memberAttendeeLimit shouldBe 10
        meetingLimits.guestAttendeeLimit shouldBe 10
    }

    @Test
    fun `fail when create meeting limits with member attendees limits is less than guests attendees limits`() {
        val exception = shouldThrow<IllegalArgumentException> {
            MeetingAttendeeLimits(5, 10)
        }

        exception.message shouldBe
                "Members meeting attendees limit must be greater than guests meeting attendees limits."
    }

    @Test
    fun `create meeting limits successfully when member attendees limits is not defined`() {
        val meetingLimits = MeetingAttendeeLimits(guestAttendeeLimit = 10)

        meetingLimits.memberAttendeeLimit shouldBe null
        meetingLimits.guestAttendeeLimit shouldBe 10
    }

    @Test
    fun `fail when create meeting limits with negative member attendees limits`() {
        val exception = shouldThrow<IllegalArgumentException> {
            MeetingAttendeeLimits(-1, 10)
        }

        exception.message shouldBe "Members meeting attendees limit must be positive."
    }

    @Test
    fun `fail when create meeting limits with negative guest attendees limits`() {
        val exception = shouldThrow<IllegalArgumentException> {
            MeetingAttendeeLimits(10, -1)
        }

        exception.message shouldBe "Guests meeting attendees limit must be positive."
    }
}
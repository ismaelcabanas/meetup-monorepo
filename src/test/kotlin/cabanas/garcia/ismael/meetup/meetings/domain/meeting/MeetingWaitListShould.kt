package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingWaitListShould {
    @Test
    fun `not sign up member to wait list when meeting has started`() {
        val meeting = MeetingMother.started()

        val exception = shouldThrow<MeetingCannotChangedAfterHasStartedException> {
            meeting.signUpMemberToWaitList(MemberId(SOME_MEMBER_ID))
        }

        exception.message shouldBe "Meeting cannot be changed after start."
    }
    
    @Test
    fun `not sign up member to wait list when term ended`() {
        TODO("Not implemented yet")    
    }
    
    @Test
    fun `not sign up member to wait list when member is not a member of meeting group`() {
        TODO("Not implemented yet")
    }

    @Test
    fun `not sign up member to wait list when member is already in wait list`() {
        TODO("Not implemented yet")
    }

    @Test
    fun `sign up member to wait list successfully`() {
        TODO("Not implemented yet")
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
    }
}
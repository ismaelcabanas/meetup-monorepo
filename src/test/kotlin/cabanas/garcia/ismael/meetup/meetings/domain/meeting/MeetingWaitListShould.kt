package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingWaitListMemberAdded
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.DomainException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingWaitListShould {
    @Test
    fun `not sign up member to wait list when meeting has started`() {
        val meetingGroup = MeetingGroupMother.create()
        val meeting = MeetingMother.started()

        val exception = shouldThrow<DomainException> {
            meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))
        }

        exception.message shouldBe "Meeting cannot be changed after start."
    }
    
    @Test
    fun `not sign up member to wait list when sign up out of enrolment term to meeting`() {
        val meetingGroup = MeetingGroupMother.create()
        val meeting = MeetingMother.outOfEnrolmentTerm()

        val exception = shouldThrow<DomainException> {
            meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))
        }

        exception.message shouldBe "Attendee can be added only in enrolment term."
    }
    
    @Test
    fun `not sign up member to wait list when member is not a member of meeting group`() {
        val meetingGroup = MeetingGroupMother.create()
        val meeting = MeetingMother.inEnrolmentTerm()

        val exception = shouldThrow<DomainException> {
            meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))
        }

        exception.message shouldBe "Member on waitlist must be a member of meeting group."
    }

    @Test
    fun `not sign up member to wait list when member is already in wait list`() {
        val meetingGroup = MeetingGroupMother.withMember(SOME_MEMBER_ID)
        val meeting = MeetingMother.inEnrolmentTerm()
        meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))

        val exception = shouldThrow<DomainException> {
            meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))
        }

        exception.message shouldBe "Member already exist on wait list."
    }

    @Test
    fun `sign up member to wait list successfully`() {
        val meetingGroup = MeetingGroupMother.withMember(SOME_MEMBER_ID)
        val meeting = MeetingMother.inEnrolmentTerm()

        meeting.signUpMemberToWaitList(meetingGroup, MemberId(SOME_MEMBER_ID))

        meeting.waitListMembers() shouldContain
                MeetingWaitListMember(meeting.id, MemberId(SOME_MEMBER_ID))
        meeting.events() shouldContain
                MeetingWaitListMemberAdded(
                    meeting.id.value,
                    SOME_MEMBER_ID
                )
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
    }
}
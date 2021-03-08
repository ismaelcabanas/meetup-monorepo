package cabanas.garcia.ismael.meetup.meetings.application.signoffmemberfromwaitlist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingMother
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingWaitListMemberRemoved
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.kotest.matchers.collections.shouldContain
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignOffMemberFromWaitListCommandHandlerShould {
    var meetingRepository = mockk<MeetingRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)
    private lateinit var commandHandler: SignOffMemberFromWaitListCommandHandler

    @BeforeEach
    fun `configure sut`() {
        val meetingNotStarted = MeetingMother.notStartedYet(SOME_MEETING_ID)
        val meetingGroupWithMember = MeetingGroupMother.withMember(SOME_MEMBER_ID)
        meetingNotStarted.signUpMemberToWaitList(meetingGroupWithMember, MemberId(SOME_MEMBER_ID))
        every { meetingRepository.findById(MeetingId(SOME_MEETING_ID)) } returns meetingNotStarted
        commandHandler = SignOffMemberFromWaitListCommandHandler(meetingRepository, eventBus)
    }

    @Test
    fun `persist meeting wait list`() {
        val command = SignOffMemberFromWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)

        commandHandler.handle(command)

        verify {
            meetingRepository.save(any())
        }
    }

    @Test
    fun `publish member removed from wait list event`() {
        val command = SignOffMemberFromWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)

        commandHandler.handle(command)

        verify {
            eventBus.publish(
                match {
                    it.contains(MeetingWaitListMemberRemoved(SOME_MEETING_ID, SOME_MEMBER_ID))
                }
            )
        }
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
        private const val SOME_MEETING_ID = "some meeting id"
    }
}
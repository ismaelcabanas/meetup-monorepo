package cabanas.garcia.ismael.meetup.meetings.application.signupmembertowaillist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingMother
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingWaitListMemberAdded
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupMother
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignUpMemberToWaitListCommandHandlerShould {
    var meetingRepository = mockk<MeetingRepository>(relaxed = true)
    var meetingGroupRepository = mockk<MeetingGroupRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)
    private lateinit var commandHandler: SignUpMemberToWaitListCommandHandler

    @BeforeEach
    fun `configure sut`() {
        val meetingNotStarted = MeetingMother.notStartedYet(SOME_MEETING_ID)
        val meetingGroupWithMember = MeetingGroupMother.withMember(SOME_MEMBER_ID)
        every { meetingRepository.findById(MeetingId(SOME_MEETING_ID)) } returns meetingNotStarted
        every { meetingGroupRepository.findBy(meetingNotStarted.meetingGroup.id) } returns meetingGroupWithMember
        commandHandler = SignUpMemberToWaitListCommandHandler(meetingGroupRepository, meetingRepository, eventBus)
    }

    @Test
    fun `persist meeting wait list with member`() {
        val command = SignUpMemberToWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)

        commandHandler.handle(command)

        verify {
            meetingRepository.save(any())
        }
    }

    @Test
    fun `publish member added to wait list event`() {
        val command = SignUpMemberToWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)

        commandHandler.handle(command)

        verify {
            eventBus.publish(
                listOf(MeetingWaitListMemberAdded(SOME_MEETING_ID, SOME_MEMBER_ID))
            )
        }
    }

    private companion object {
        private const val SOME_MEMBER_ID = "some member id"
        private const val SOME_MEETING_ID = "some meeting id"
    }
}
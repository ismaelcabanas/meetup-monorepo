package cabanas.garcia.ismael.meetup.meetings.application.signupmembertowaillist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingMother
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingTerm
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingWaitListMemberAdded
import cabanas.garcia.ismael.meetup.shared.service.EventBus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant
import java.time.Period
import org.junit.jupiter.api.Test

class SignUpMemberToWaitListCommandHandlerShould {
    var meetingRepository = mockk<MeetingRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)

    @Test
    fun `persist meeting wait list with member`() {
        every { meetingRepository.findById(MeetingId(SOME_MEETING_ID)) } returns
                MeetingMother.notStartedYet(
                    SOME_MEETING_ID
                )
        val command = SignUpMemberToWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)
        val commandHandler = SignUpMemberToWaitListCommandHandler(meetingRepository, eventBus)

        commandHandler.handle(command)

        verify {
            meetingRepository.save(any())
        }
    }

    @Test
    fun `publish member added to wait list event`() {
        every { meetingRepository.findById(MeetingId(SOME_MEETING_ID)) } returns
                MeetingMother.notStartedYet(
                    SOME_MEETING_ID
                )
        val command = SignUpMemberToWaitListCommand(SOME_MEETING_ID, SOME_MEMBER_ID)
        val commandHandler = SignUpMemberToWaitListCommandHandler(meetingRepository, eventBus)

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
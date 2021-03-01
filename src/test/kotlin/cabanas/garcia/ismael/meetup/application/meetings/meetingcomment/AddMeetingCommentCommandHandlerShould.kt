package cabanas.garcia.ismael.meetup.application.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.Meeting
import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment.MeetingCommentCreated
import cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment.MeetingCommentRepository
import cabanas.garcia.ismael.meetup.domain.shared.service.EventBus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AddMeetingCommentCommandHandlerShould {
    var eventBus = mockk<EventBus>(relaxed = true)
    var meetingRepository = mockk<MeetingRepository>(relaxed = true)
    var meetingCommentRepository = mockk<MeetingCommentRepository>(relaxed = true)

    @Test
    fun `add comment to meeting`() {
        /*
        every {
            meetingRepository.findById(MeetingId(SOME_MEETING_ID))
        }.returns(
            Meeting(MeetingId(SOME_MEETING_ID))
        )
        val command = AddMeetingCommentCommand(
            SOME_MEETING_COMMENT_ID,
            SOME_MEETING_ID,
            SOME_USER_ID,
            SOME_COMMENT
        )
        val commandHandler = AddMeetingCommentCommandHandler(meetingRepository, meetingCommentRepository, eventBus)

        commandHandler.handle(command)

        verify {
            eventBus.publish(
                listOf(
                    MeetingCommentCreated(
                        SOME_MEETING_COMMENT_ID,
                        SOME_MEETING_ID,
                        SOME_USER_ID,
                        SOME_COMMENT
                    )
                )
            )
        }
         */
    }

    private companion object {
        private const val SOME_MEETING_COMMENT_ID = "some meeting comment id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_MEETING_ID = "some meeting id"
        private const val SOME_COMMENT = "some comment"
    }
}
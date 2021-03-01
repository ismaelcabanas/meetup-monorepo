package cabanas.garcia.ismael.meetup.meetings.application.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment.MeetingCommentRepository
import cabanas.garcia.ismael.meetup.shared.service.EventBus
import io.mockk.mockk
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
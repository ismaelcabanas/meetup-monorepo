package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingCommentShould {
    @Test
    fun `create a successful meeting comment`() {
        val meetingComment = MeetingCommentFactory.create(
            SOME_MEETING_COMMENT_ID,
            SOME_MEETING_ID,
            SOME_USER_ID,
            SOME_COMMENT
        )

        meetingComment.events() shouldContain
                MeetingCommentCreated(
                    SOME_MEETING_COMMENT_ID,
                    SOME_MEETING_ID,
                    SOME_USER_ID,
                    SOME_COMMENT,
                    meetingComment.date
                )
    }

    @Test
    fun `fail when comment is empty`() {
        val exception = shouldThrow<CommentRequiredException> {
            MeetingCommentFactory.create(
                SOME_MEETING_COMMENT_ID,
                SOME_MEETING_ID,
                SOME_USER_ID,
                EMPTY_COMMENT
            )
        }

        exception.message shouldBe "Meeting comment is required."
    }

    private companion object {
        private const val SOME_MEETING_COMMENT_ID = "some comment id"
        private const val SOME_MEETING_ID = "some meeting id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_COMMENT = "some comment"
        private const val EMPTY_COMMENT = ""
    }
}
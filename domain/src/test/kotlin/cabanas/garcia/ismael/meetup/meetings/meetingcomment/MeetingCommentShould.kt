package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfigurationMother
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroupMother
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingCommentShould {
    private val meetingGroupWithMember: MeetingGroup = MeetingGroupMother.withMember(SOME_AUTHOR_ID)
    private val meetingGroupConfigurationWithCommentsDisabled: MeetingGroupConfiguration =
        MeetingGroupConfigurationMother.withCommentsDisabled()
    private val meetingGroupConfigurationWithCommentsEnabled: MeetingGroupConfiguration =
        MeetingGroupConfigurationMother.withCommentsEnabled()

    @Test
    fun `create a successful meeting comment`() {
        val meetingComment = MeetingCommentFactory.create(
            SOME_MEETING_COMMENT_ID,
            SOME_MEETING_ID,
            SOME_AUTHOR_ID,
            SOME_COMMENT,
            meetingGroupWithMember,
            meetingGroupConfigurationWithCommentsEnabled
        )

        meetingComment.events() shouldContain
                MeetingCommentCreated(
                    SOME_MEETING_COMMENT_ID,
                    SOME_MEETING_ID,
                    SOME_AUTHOR_ID,
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
                SOME_AUTHOR_ID,
                EMPTY_COMMENT,
                meetingGroupWithMember,
                meetingGroupConfigurationWithCommentsEnabled
            )
        }

        exception.message shouldBe "Meeting comment is required."
    }

    @Test
    fun `fail when add comment by author that is not a meeting group's member`() {
        val exception = shouldThrow<CommentCanBeAddedOnlyByMeetingGroupMembersException> {
            MeetingCommentFactory.create(
                SOME_MEETING_COMMENT_ID,
                SOME_MEETING_ID,
                SOME_NO_MEMBER_ID,
                SOME_COMMENT,
                meetingGroupWithMember,
                meetingGroupConfigurationWithCommentsEnabled
            )
        }

        exception.message shouldBe "User '$SOME_NO_MEMBER_ID' is not member of meeting group '$SOME_MEETING_GROUP_ID'."
    }

    @Test
    fun `fail when add comment for meeting is not enabled`() {
        val exception = shouldThrow<CommentCanBeAddedIfCommentMeetingIsEnableException> {
            MeetingCommentFactory.create(
                SOME_MEETING_COMMENT_ID,
                SOME_MEETING_ID,
                SOME_AUTHOR_ID,
                SOME_COMMENT,
                meetingGroupWithMember,
                meetingGroupConfigurationWithCommentsDisabled
            )
        }

        exception.message shouldBe "Comments for meeting is disabled."
    }

    private companion object {
        private const val SOME_MEETING_COMMENT_ID = "some comment id"
        private const val SOME_MEETING_ID = "some meeting id"
        private const val SOME_MEETING_GROUP_ID = "some meeting group id"
        private const val SOME_AUTHOR_ID = "some author id"
        private const val SOME_NO_MEMBER_ID = "some no member id"
        private const val SOME_COMMENT = "some comment"
        private const val EMPTY_COMMENT = ""
    }
}
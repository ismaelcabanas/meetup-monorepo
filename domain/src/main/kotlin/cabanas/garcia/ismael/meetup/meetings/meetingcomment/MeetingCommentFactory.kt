package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.member.MemberId

object MeetingCommentFactory {
    fun create(
        meetingCommentId: String,
        meetingId: String,
        authorId: String,
        comment: String
    ): MeetingComment =
        MeetingComment(
            MeetingCommentId(meetingCommentId),
            MeetingId(meetingId),
            MemberId(authorId),
            Comment(comment)
        ).create()
}
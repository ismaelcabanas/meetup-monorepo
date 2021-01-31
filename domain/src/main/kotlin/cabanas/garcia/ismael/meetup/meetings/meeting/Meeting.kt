package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingComment
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentFactory
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentId
import cabanas.garcia.ismael.meetup.meetings.member.MemberId

data class Meeting(
    val id: MeetingId
) {

    fun addComment(meetingCommentId: MeetingCommentId, authorId: MemberId, comment: String): MeetingComment =
        MeetingCommentFactory.create(
            meetingCommentId,
            id,
            authorId,
            comment
        )

}

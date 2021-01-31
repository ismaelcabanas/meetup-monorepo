package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.member.MemberId

object MeetingCommentFactory {
    fun create(
        meetingCommentId: MeetingCommentId,
        meetingId: MeetingId,
        authorId: MemberId,
        comment: String
    ): MeetingComment =
        MeetingComment(
            meetingCommentId,
            meetingId,
            authorId,
            comment
        ).also {
            it.registerDomainEvent(
                MeetingCommentCreated(
                    it.id.value,
                    it.meetingId.value,
                    it.memberId.value,
                    it.comment
                )
            )
        }
}
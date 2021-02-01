package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingCommentCreated(
    val meetingCommentId: String,
    val meetingId: String,
    val authorId: String,
    val comment: String,
    val commentDate: Instant
) : DomainEvent

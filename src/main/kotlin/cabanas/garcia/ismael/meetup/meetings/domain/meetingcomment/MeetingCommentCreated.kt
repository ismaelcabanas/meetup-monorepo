package cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment

import cabanas.garcia.ismael.meetup.shared.DomainEvent
import java.time.Instant

data class MeetingCommentCreated(
    val meetingCommentId: String,
    val meetingId: String,
    val authorId: String,
    val comment: String,
    val commentDate: Instant
) : DomainEvent

package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetingCommentCreated(
    val meetingCommentId: String,
    val meetingId: String,
    val userId: String,
    val comment: String
) : DomainEvent

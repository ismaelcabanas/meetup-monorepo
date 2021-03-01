package cabanas.garcia.ismael.meetup.meetings.application.meetingcomment

data class AddMeetingCommentCommand(
    val meetingCommentId: String,
    val meetingId: String,
    val userId: String,
    val comment: String
)

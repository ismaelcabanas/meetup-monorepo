package cabanas.garcia.ismael.meetup.meetings.meetingcomment

data class AddMeetingCommentCommand(
    val meetingCommentId: String,
    val meetingId: String,
    val userId: String,
    val comment: String
)

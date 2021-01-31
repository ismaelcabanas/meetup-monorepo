package cabanas.garcia.ismael.meetup.meetings.meetingcomment

interface MeetingCommentRepository {
    fun save(meetingComment: MeetingComment)
}
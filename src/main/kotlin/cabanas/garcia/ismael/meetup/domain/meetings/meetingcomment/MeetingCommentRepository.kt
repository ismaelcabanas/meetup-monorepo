package cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment

interface MeetingCommentRepository {
    fun save(meetingComment: MeetingComment)
}
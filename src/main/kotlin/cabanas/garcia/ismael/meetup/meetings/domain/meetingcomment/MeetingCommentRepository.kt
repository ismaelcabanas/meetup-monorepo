package cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment

interface MeetingCommentRepository {
    fun save(meetingComment: MeetingComment)
}
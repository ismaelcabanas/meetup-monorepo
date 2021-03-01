package cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment

class Comment(val value: String) {
    init {
        if (value == "") {
            throw CommentRequiredException()
        }
    }
}

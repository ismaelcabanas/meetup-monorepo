package cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment

class Comment(val value: String) {
    init {
        if (value == "") {
            throw CommentRequiredException()
        }
    }
}

package cabanas.garcia.ismael.meetup.meetings.meeting

import java.lang.IllegalArgumentException

data class MeetingAttendeeLimits(val memberAttendeeLimit: Int? = null, val guestAttendeeLimit: Int) {
    init {
        if (guestAttendeeLimit < 0) {
            throw IllegalArgumentException("Guests meeting attendees limit must be positive.")
        }
        memberAttendeeLimit?.let {
            if (memberAttendeeLimit < 0) {
                throw IllegalArgumentException("Members meeting attendees limit must be positive.")
            }
            if (it < guestAttendeeLimit) {
                throw IllegalArgumentException("Members meeting attendees limit must be greater than guests meeting" +
                        " attendees limits.")
            }
        }
    }
}

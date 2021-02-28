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

    fun guestsAboveOf(guestsNumber: Int): Boolean = guestsNumber > guestAttendeeLimit

    fun attendeesAboveOf(attendeesNumber: Int) =
        if (memberAttendeeLimit != null) {
            attendeesNumber > memberAttendeeLimit
        }
        else {
            false
        }
}

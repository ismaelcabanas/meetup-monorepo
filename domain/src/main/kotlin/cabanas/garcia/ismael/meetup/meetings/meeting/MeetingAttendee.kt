package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import java.time.Instant

data class MeetingAttendee(
    val id: MemberId,
    val removingPersonId: MemberId? = null,
    val removingDate: Instant? = null,
    val removingReason: String? = null
) {
    fun remove(removingPersonId: MemberId, removingDate: Instant, reason: String): MeetingAttendee =
        MeetingAttendee(id, removingPersonId, removingDate, reason)
}

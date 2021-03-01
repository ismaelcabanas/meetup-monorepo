package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
import java.time.Instant

data class MeetingAttendee(
    val id: MemberId,
    val meetingId: MeetingId,
    val enrolmentDate: Instant,
    val guestsNumber: Int? = 0,
    val removingPersonId: MemberId? = null,
    val removingDate: Instant? = null,
    val removingReason: String? = null
) {
    fun remove(removingPersonId: MemberId, removingDate: Instant, reason: String): MeetingAttendee =
        MeetingAttendee(
            id,
            meetingId,
            enrolmentDate,
            removingPersonId = removingPersonId,
            removingDate = removingDate,
            removingReason = reason)

    fun attendeeWithGuestsNumber() = if (guestsNumber != null) guestsNumber + 1 else 0

}

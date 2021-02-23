package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingAttendeeRemoved(
    val meetingId: String,
    val memberId: String,
    val removingPersonId: String,
    val removingDate: Instant,
    val reason: String
) : DomainEvent

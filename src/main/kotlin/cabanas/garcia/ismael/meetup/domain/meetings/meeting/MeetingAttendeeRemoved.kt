package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetingAttendeeRemoved(
    val meetingId: String,
    val memberId: String,
    val removingPersonId: String,
    val removingDate: Instant,
    val reason: String
) : DomainEvent

package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class MeetingAttendeeRemoved(
    val meetingId: String,
    val memberId: String,
    val removingPersonId: String,
    val removingDate: Instant,
    val reason: String
) : DomainEvent

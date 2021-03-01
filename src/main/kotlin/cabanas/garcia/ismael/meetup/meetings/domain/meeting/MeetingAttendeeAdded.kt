package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.DomainEvent
import java.time.Instant

data class MeetingAttendeeAdded(
    val attendeeId: String,
    val meetingId: String,
    val enrolmentDate: Instant
) : DomainEvent

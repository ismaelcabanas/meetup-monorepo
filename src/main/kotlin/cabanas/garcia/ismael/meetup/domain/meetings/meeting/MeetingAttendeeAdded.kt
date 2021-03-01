package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetingAttendeeAdded(
    val attendeeId: String,
    val meetingId: String,
    val enrolmentDate: Instant
) : DomainEvent

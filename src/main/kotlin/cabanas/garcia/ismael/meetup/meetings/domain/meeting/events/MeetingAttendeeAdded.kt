package cabanas.garcia.ismael.meetup.meetings.domain.meeting.events

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class MeetingAttendeeAdded(
    val attendeeId: String,
    val meetingId: String,
    val enrolmentDate: Instant
) : DomainEvent

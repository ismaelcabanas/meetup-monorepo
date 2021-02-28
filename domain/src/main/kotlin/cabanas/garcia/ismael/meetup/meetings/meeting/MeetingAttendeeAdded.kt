package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingAttendeeAdded(
    val attendeeId: String,
    val meetingId: String,
    val enrolmentDate: Instant
) : DomainEvent

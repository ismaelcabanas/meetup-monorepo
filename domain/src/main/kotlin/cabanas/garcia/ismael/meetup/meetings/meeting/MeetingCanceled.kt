package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingCanceled(
    val meetingId: String,
    val cancelMemberId: String,
    val cancelDate: Instant
) : DomainEvent
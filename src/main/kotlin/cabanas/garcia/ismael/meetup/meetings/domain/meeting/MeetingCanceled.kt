package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.DomainEvent
import java.time.Instant

data class MeetingCanceled(
    val meetingId: String,
    val cancelMemberId: String,
    val cancelDate: Instant
) : DomainEvent
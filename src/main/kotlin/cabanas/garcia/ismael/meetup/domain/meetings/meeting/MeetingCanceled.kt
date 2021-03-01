package cabanas.garcia.ismael.meetup.domain.meetings.meeting

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetingCanceled(
    val meetingId: String,
    val cancelMemberId: String,
    val cancelDate: Instant
) : DomainEvent
package cabanas.garcia.ismael.meetup.meetings.domain.meeting.events

import cabanas.garcia.ismael.meetup.shared.DomainEvent

data class MeetingWaitListMemberAdded(val meetingId: String, val memberId: String) : DomainEvent

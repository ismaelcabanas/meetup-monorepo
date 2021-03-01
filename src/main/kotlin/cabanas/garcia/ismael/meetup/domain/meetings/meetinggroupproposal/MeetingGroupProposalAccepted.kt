package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

data class MeetingGroupProposalAccepted(val meetingGroupProposalId: String): DomainEvent

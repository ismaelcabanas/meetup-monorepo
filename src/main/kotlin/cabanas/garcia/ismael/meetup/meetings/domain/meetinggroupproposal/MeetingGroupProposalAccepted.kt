package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class MeetingGroupProposalAccepted(val meetingGroupProposalId: String): DomainEvent

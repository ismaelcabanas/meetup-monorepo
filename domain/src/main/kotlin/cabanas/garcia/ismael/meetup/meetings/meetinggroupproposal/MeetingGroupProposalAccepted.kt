package cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetingGroupProposalAccepted(val meetingGroupProposalId: String): DomainEvent

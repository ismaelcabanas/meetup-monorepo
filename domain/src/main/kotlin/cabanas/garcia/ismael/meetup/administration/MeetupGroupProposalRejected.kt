package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetupGroupProposalRejected(val meetupGroupProposalId: String, val proposalUserId: String) : DomainEvent

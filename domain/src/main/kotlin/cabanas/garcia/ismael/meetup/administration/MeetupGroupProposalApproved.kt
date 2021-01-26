package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class MeetupGroupProposalApproved(val meetupGroupProposalId: String, val proposalUserId: String) : DomainEvent
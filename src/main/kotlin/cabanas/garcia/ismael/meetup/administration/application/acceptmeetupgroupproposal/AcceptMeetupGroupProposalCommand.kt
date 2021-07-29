package cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal

import cabanas.garcia.ismael.meetup.shared.application.Command

data class AcceptMeetupGroupProposalCommand(val meetupGroupProposalId: String, val userId: String) : Command
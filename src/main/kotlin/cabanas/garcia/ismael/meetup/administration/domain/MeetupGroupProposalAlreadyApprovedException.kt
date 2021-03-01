package cabanas.garcia.ismael.meetup.administration.domain

class MeetupGroupProposalAlreadyApprovedException(private val meetupGroupProposalId: MeetupGroupProposalId)
    : Exception("Meetup group proposal '${meetupGroupProposalId.value}' already approved.")

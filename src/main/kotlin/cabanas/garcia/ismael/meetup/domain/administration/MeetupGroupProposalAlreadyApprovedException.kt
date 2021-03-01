package cabanas.garcia.ismael.meetup.domain.administration

class MeetupGroupProposalAlreadyApprovedException(private val meetupGroupProposalId: MeetupGroupProposalId)
    : Exception("Meetup group proposal '${meetupGroupProposalId.value}' already approved.")
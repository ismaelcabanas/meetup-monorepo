package cabanas.garcia.ismael.meetup.administration

class MeetupGroupProposalAlreadyApprovedException(private val meetupGroupProposalId: String)
    : Exception("Meetup group proposal '$meetupGroupProposalId' already approved.")

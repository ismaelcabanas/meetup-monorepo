package cabanas.garcia.ismael.meetup.administration

data class MeetupGroupProposalAlreadyRejectedException(private val meetupGroupProposalId: String)
    : Exception("Meetup group proposal '${meetupGroupProposalId}' already rejected.")

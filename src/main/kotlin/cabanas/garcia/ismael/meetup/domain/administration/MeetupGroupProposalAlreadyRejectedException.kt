package cabanas.garcia.ismael.meetup.domain.administration

data class MeetupGroupProposalAlreadyRejectedException(private val meetupGroupProposalId: MeetupGroupProposalId)
    : Exception("Meetup group proposal '${meetupGroupProposalId.value}' already rejected.")

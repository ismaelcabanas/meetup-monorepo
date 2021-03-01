package cabanas.garcia.ismael.meetup.administration.domain

data class MeetupGroupProposalAlreadyRejectedException(private val meetupGroupProposalId: MeetupGroupProposalId)
    : Exception("Meetup group proposal '${meetupGroupProposalId.value}' already rejected.")

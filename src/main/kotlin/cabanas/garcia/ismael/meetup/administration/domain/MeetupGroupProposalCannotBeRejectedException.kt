package cabanas.garcia.ismael.meetup.administration.domain

data class MeetupGroupProposalCannotBeRejectedException(val userId: UserId)
    : Exception("User '${userId.value}' is not Admin and cannot reject the proposal.")

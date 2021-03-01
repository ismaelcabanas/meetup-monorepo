package cabanas.garcia.ismael.meetup.administration.domain

data class MeetupGroupProposalCannotBeApprovedException(val userId: UserId)
    : Exception("User '${userId.value}' is not Admin and cannot approve the proposal.")

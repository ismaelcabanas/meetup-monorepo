package cabanas.garcia.ismael.meetup.administration

data class MeetupGroupProposalCannotBeApprovedException(val userId: UserId)
    : Exception("User '${userId.value}' is not Admin and cannot approve the proposal.")

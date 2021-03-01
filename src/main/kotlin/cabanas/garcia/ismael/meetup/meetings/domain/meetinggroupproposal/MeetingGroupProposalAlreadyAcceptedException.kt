package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

data class MeetingGroupProposalAlreadyAcceptedException(val id: MeetingGroupProposalId)
    : Exception("Meeting group proposal '${id.value}' already accepted.") {

}

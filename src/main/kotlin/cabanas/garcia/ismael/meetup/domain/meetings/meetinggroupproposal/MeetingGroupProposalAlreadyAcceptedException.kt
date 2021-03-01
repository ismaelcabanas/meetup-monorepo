package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroupproposal

data class MeetingGroupProposalAlreadyAcceptedException(val id: MeetingGroupProposalId)
    : Exception("Meeting group proposal '${id.value}' already accepted.") {

}

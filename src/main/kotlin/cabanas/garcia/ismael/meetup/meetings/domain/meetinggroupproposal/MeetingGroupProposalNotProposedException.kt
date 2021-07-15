package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

class MeetingGroupProposalNotProposedException(val id: MeetingGroupProposalId)
    : Exception("Meeting group proposal '${id.value}' has not been proposed.")
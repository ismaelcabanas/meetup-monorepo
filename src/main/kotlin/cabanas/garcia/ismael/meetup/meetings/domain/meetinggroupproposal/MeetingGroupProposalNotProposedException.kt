package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import java.lang.RuntimeException

class MeetingGroupProposalNotProposedException(val id: MeetingGroupProposalId)
    : RuntimeException("Meeting group proposal '${id.value}' has not been proposed.")
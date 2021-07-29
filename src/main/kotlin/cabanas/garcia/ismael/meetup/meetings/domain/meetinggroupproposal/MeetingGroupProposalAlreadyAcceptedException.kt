package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import java.lang.RuntimeException

data class MeetingGroupProposalAlreadyAcceptedException(val id: MeetingGroupProposalId)
    : RuntimeException("Meeting group proposal '${id.value}' already accepted.") {

}

package cabanas.garcia.ismael.meetup.meetings.application.createmeetinggroupproposal

import cabanas.garcia.ismael.meetup.shared.application.Command
import java.time.Instant

data class CreateMeetingGroupProposalCommand(
    val meetingGroupProposalId: String? = null,
    val meetingGroupProposalName: String? = null,
    val meetingGroupProposalDescription: String? = null,
    val meetingGroupProposalCountry: String? = null,
    val meetingGroupProposalCity: String? = null,
    val meetingGroupProposalDate: Instant? = null
) : Command

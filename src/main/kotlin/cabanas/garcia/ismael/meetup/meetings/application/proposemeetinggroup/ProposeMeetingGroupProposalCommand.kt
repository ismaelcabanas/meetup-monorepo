package cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup

import cabanas.garcia.ismael.meetup.shared.application.Command
import java.time.Instant

class ProposeMeetingGroupProposalCommand private constructor(
    val meetingGroupProposalId: String?,
    val proposalMemberId: String?,
    val meetingGroupProposalName: String?,
    val meetingGroupProposalDescription: String?,
    val meetingGroupProposalCountry: String?,
    val meetingGroupProposalCity: String?,
    val meetingGroupProposalDate: Instant?
) : Command {
    data class Builder(
        var meetingGroupProposalId: String? = null,
        var proposalMemberId: String? = null,
        var meetingGroupProposalName: String? = null,
        var meetingGroupProposalDescription: String? = null,
        var meetingGroupProposalCountry: String? = null,
        var meetingGroupProposalCity: String? = null,
        var meetingGroupProposalDate: Instant? = null
    ) {
        fun withMeetingGroupProposalId(value: String) = apply { this.meetingGroupProposalId = value }
        fun withProposalMemberId(value: String) = apply { this.proposalMemberId = value }
        fun withMeetingGroupProposalName(value: String) = apply { this.meetingGroupProposalName = value }
        fun withMeetingGroupProposalDescription(value: String) = apply { this.meetingGroupProposalDescription = value }
        fun withMeetingGroupProposalCountry(value: String) = apply { this.meetingGroupProposalCountry = value }
        fun withMeetingGroupProposalCity(value: String) = apply { this.meetingGroupProposalCity = value }
        fun withMeetingGroupProposalDate(value: Instant) = apply { this.meetingGroupProposalDate = value }
        fun build() = ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId,
            proposalMemberId,
            meetingGroupProposalName,
            meetingGroupProposalDescription,
            meetingGroupProposalCountry,
            meetingGroupProposalCity,
            meetingGroupProposalDate
        )
    }
}

package cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import java.time.Instant

object MeetingGroupProposalFactory {
    fun propose(
        meetingGroupProposalId: MeetingGroupProposalId,
        proposalMemberId: MemberId,
        name: String,
        description: String,
        meetingGroupLocation: MeetingGroupLocation,
        proposalDate: Instant
    ): MeetingGroupProposal =
        MeetingGroupProposal(
            meetingGroupProposalId,
            proposalMemberId,
            name,
            description,
            meetingGroupLocation,
            proposalDate
        ).new()

}
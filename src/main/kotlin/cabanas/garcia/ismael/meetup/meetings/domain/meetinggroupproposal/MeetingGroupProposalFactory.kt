package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
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
        ).propose()
}
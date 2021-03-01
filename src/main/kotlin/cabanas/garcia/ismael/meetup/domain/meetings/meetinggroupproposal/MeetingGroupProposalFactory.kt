package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
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
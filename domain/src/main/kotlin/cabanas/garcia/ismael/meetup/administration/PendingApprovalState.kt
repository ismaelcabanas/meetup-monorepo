package cabanas.garcia.ismael.meetup.administration

import java.time.Instant

class PendingApprovalState : MeetupGroupProposalState {
    override fun pendingApprove(meetupGroupProposal: MeetupGroupProposal): MeetupGroupProposal =
        meetupGroupProposal.changeState(PendingApprovalState())

    override fun approve(meetupGroupProposal: MeetupGroupProposal, approveBy: User): MeetupGroupProposal =
        meetupGroupProposal.changeState(ApprovedState(approveBy, Instant.now()))

    override fun reject(
        meetupGroupProposal: MeetupGroupProposal,
        rejectBy: User,
        rejectedReason: String)
    : MeetupGroupProposal =
        meetupGroupProposal.changeState(RejectedState(rejectBy, rejectedReason, Instant.now()))

    override fun value(): MeetupGroupProposalStatus =
        MeetupGroupProposalStatus.PENDING_OF_APPROVAL
}

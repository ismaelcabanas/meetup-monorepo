package cabanas.garcia.ismael.meetup.administration.domain

import java.time.Instant

data class ApprovedState(val user: User, val approveDate: Instant) : MeetupGroupProposalState {
    init {
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeApprovedException(user.id)
        }
    }

    override fun pendingApprove(meetupGroupProposal: MeetupGroupProposal): MeetupGroupProposal {
        throw IllegalStateException("Cannot pending of approval a approved meetup group proposal.")
    }

    override fun approve(meetupGroupProposal: MeetupGroupProposal, approveBy: User): MeetupGroupProposal {
        throw MeetupGroupProposalAlreadyApprovedException(meetupGroupProposal.id)
    }

    override fun reject(meetupGroupProposal: MeetupGroupProposal, rejectBy: User, rejectedReason: String): MeetupGroupProposal {
        throw IllegalStateException("Cannot reject a approved meetup group proposal.")
    }

    override fun value(): MeetupGroupProposalStatus =
        MeetupGroupProposalStatus.APPROVED
}
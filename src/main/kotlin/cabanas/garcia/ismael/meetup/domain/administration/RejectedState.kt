package cabanas.garcia.ismael.meetup.domain.administration

import java.time.Instant

data class RejectedState(val user: User, val rejectedReason: String?, val rejectDate: Instant)
    : MeetupGroupProposalState {

    init {
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeRejectedException(user.id)
        }

        if (rejectedReason.isNullOrBlank()) {
            throw MeetupGroupProposalRequireReasonException()
        }
    }

    override fun pendingApprove(meetupGroupProposal: MeetupGroupProposal): MeetupGroupProposal {
        throw IllegalStateException("Cannot propose a rejected meetup group proposal.")
    }

    override fun approve(meetupGroupProposal: MeetupGroupProposal, approveBy: User): MeetupGroupProposal {
        throw IllegalStateException("Cannot approve a rejected meetup group proposal.")
    }

    override fun reject(meetupGroupProposal: MeetupGroupProposal, rejectBy: User, rejectedReason: String): MeetupGroupProposal {
        throw MeetupGroupProposalAlreadyRejectedException(meetupGroupProposal.id)
    }

    override fun value(): MeetupGroupProposalStatus =
        MeetupGroupProposalStatus.REJECTED
}
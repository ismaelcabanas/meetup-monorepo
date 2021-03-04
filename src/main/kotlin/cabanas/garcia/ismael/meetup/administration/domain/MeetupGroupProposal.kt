package cabanas.garcia.ismael.meetup.administration.domain

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

data class MeetupGroupProposal(
    val id: MeetupGroupProposalId,
    val proposalUserId: UserId,
    val name: String,
    val description: String,
    val location: MeetupGroupLocation,
    val proposalDate: Instant
) {
    private var state: MeetupGroupProposalState
    private var events = mutableListOf<DomainEvent>()

    init {
        state = PendingApprovalState()
    }

    fun events() = events.toList()

    fun state() = state

    fun approveDate() =
        when (state) {
            is ApprovedState -> (state as ApprovedState).approveDate
            else -> null
        }

    fun rejectDate() =
        when (state) {
            is RejectedState -> (state as RejectedState).rejectDate
            else -> null
        }

    fun proposal(): MeetupGroupProposal =
        this.apply {
            this.state.pendingApprove(this)
            this.events.add(
                MeetupGroupProposalCreated(
                    id.value,
                    proposalUserId.value,
                    name,
                    description,
                    location.country,
                    location.city,
                    proposalDate
                )
            )
        }

    fun approve(approveBy: User): MeetupGroupProposal =
        this.apply {
            state.approve(this, approveBy)
            events.add(
                MeetupGroupProposalApproved(
                    id.value,
                    proposalUserId.value,
                    approveDate()!!
                )
            )
        }

    fun reject(rejectBy: User, rejectedReason: String): MeetupGroupProposal =
        this.apply {
            state.reject(this, rejectBy, rejectedReason)
            events.add(
                MeetupGroupProposalRejected(
                    id.value,
                    proposalUserId.value,
                    rejectedReason,
                    rejectDate()!!
                )
            )
        }

    fun changeState(newState: MeetupGroupProposalState): MeetupGroupProposal =
        this.apply {
            state = newState
        }
}

package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposal(
    val id: MeetupGroupProposalId,
    val proposalUserId: UserId,
    val name: String,
    val description: String,
    val location: MeetupGroupLocation,
    val proposalDate: Instant,
    val status: MeetupGroupProposalStatus = MeetupGroupProposalStatus.PENDING_OF_APPROVAL,
    val decision: MeetupGroupProposalDecision = MeetupGroupProposalDecision()
) {
    private var events: List<DomainEvent> = mutableListOf()

    private constructor(
        id: MeetupGroupProposalId,
        proposalUserId: UserId,
        name: String,
        description: String,
        location: MeetupGroupLocation,
        date: Instant,
        status: MeetupGroupProposalStatus,
        decision: MeetupGroupProposalDecision,
        events: List<DomainEvent>
    ) : this(
        id,
        proposalUserId,
        name,
        description,
        location,
        date,
        status,
        decision
    ) {
        this.events = events
    }

    fun events() = events.toList()

    fun proposal(): MeetupGroupProposal {
        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            proposalDate,
            decision.statusForDecision(),
            decision,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalCreated(id.value, proposalUserId.value, name, description, location.country, location.city, proposalDate)
            )
        )
    }

    fun approve(user: User): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.APPROVED) {
            throw MeetupGroupProposalAlreadyApprovedException(id)
        }

        val newDecision = decision.accept(Instant.now(), user)

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            proposalDate,
            newDecision.statusForDecision(),
            newDecision,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalApproved(
                    id.value,
                    proposalUserId.value,
                    name,
                    description,
                    location.country,
                    location.city,
                    newDecision.date!!
                )
            )
        )
    }

    fun reject(user: User, rejectedReason: String): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.REJECTED) {
            throw MeetupGroupProposalAlreadyRejectedException(id)
        }

        val newDecision = decision.reject(Instant.now(), user, rejectedReason)

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            proposalDate,
            newDecision.statusForDecision(),
            newDecision,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalRejected(
                    id.value,
                    proposalUserId.value,
                    newDecision.rejectedReason!!,
                    newDecision.date!!
                )
            )
        )
    }

}

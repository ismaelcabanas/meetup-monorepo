package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposal(
    val id: MeetupGroupProposalId,
    val proposalUserId: String,
    val name: String,
    val description: String,
    val location: MeetupLocation,
    val date: Instant,
    val status: MeetupGroupProposalStatus = MeetupGroupProposalStatus.PENDING_OF_APPROVAL
) {
    private var events: List<DomainEvent> = mutableListOf()

    private constructor(
        id: MeetupGroupProposalId,
        userId: String,
        name: String,
        description: String,
        location: MeetupLocation,
        date: Instant,
        status: MeetupGroupProposalStatus,
        events: List<DomainEvent>
    ) : this(
        id,
        userId,
        name,
        description,
        location,
        date,
        status
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
            date,
            MeetupGroupProposalStatus.PENDING_OF_APPROVAL,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalCreated(id.value, proposalUserId, name, description, location.country, location.city, date)
            )
        )
    }

    fun approve(user: User): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.APPROVED) {
            throw MeetupGroupProposalAlreadyApprovedException(id)
        }
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeApprovedException(user.id)
        }

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            date,
            status = MeetupGroupProposalStatus.APPROVED,
            events = mutableListOf<DomainEvent>(MeetupGroupProposalApproved(id.value, proposalUserId))
        )
    }

    fun reject(user: User): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.REJECTED) {
            throw MeetupGroupProposalAlreadyRejectedException(id)
        }
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeRejectedException(user.id)
        }

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            date,
            status = MeetupGroupProposalStatus.REJECTED,
            events = mutableListOf<DomainEvent>(MeetupGroupProposalRejected(id.value, proposalUserId))
        )
    }
}

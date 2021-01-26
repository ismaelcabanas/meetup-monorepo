package cabanas.garcia.ismael.meetup.administration

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetupGroupProposal(
    val id: String,
    val proposalUserId: String,
    val name: String,
    val description: String,
    val country: String,
    val city: String,
    val date: Instant,
    val status: MeetupGroupProposalStatus = MeetupGroupProposalStatus.PENDING_OF_APPROVAL
) {
    private var events: List<DomainEvent> = mutableListOf()

    private constructor(
        id: String,
        userId: String,
        name: String,
        description: String,
        country: String,
        city: String,
        date: Instant,
        status: MeetupGroupProposalStatus,
        events: List<DomainEvent>
    ) : this(
        id,
        userId,
        name,
        description,
        country,
        city,
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
            country,
            city,
            date,
            MeetupGroupProposalStatus.PENDING_OF_APPROVAL,
            events = mutableListOf<DomainEvent>(MeetupGroupProposalCreated(id, country, city, date))
        )
    }

    fun approve(user: User): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.APPROVED) {
            throw MeetupGroupProposalAlreadyApprovedException(this.id)
        }
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeApprovedException(user.id)
        }

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            country,
            city,
            date,
            status = MeetupGroupProposalStatus.APPROVED,
            events = mutableListOf<DomainEvent>(MeetupGroupProposalApproved(this.id, this.proposalUserId))
        )
    }

    fun reject(user: User): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.REJECTED) {
            throw MeetupGroupProposalAlreadyRejectedException(this.id)
        }
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeRejectedException(user.id)
        }

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            country,
            city,
            date,
            status = MeetupGroupProposalStatus.REJECTED,
            events = mutableListOf<DomainEvent>(MeetupGroupProposalRejected(this.id, this.proposalUserId))
        )
    }
}

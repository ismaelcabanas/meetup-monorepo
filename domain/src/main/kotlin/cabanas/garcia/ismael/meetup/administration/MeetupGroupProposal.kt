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
    val rejectedReason: String? = null
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
        rejectedReason: String?,
        events: List<DomainEvent>
    ) : this(
        id,
        proposalUserId,
        name,
        description,
        location,
        date,
        status,
        rejectedReason
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
            MeetupGroupProposalStatus.PENDING_OF_APPROVAL,
            rejectedReason,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalCreated(id.value, proposalUserId.value, name, description, location.country, location.city, proposalDate)
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
            proposalDate,
            status = MeetupGroupProposalStatus.APPROVED,
            rejectedReason = rejectedReason,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalApproved(
                    id.value,
                    proposalUserId.value,
                    name,
                    description,
                    location.country,
                    location.city,
                    proposalDate
                )
            )
        )
    }

    fun reject(user: User, rejectedReason: String): MeetupGroupProposal {
        if (status == MeetupGroupProposalStatus.REJECTED) {
            throw MeetupGroupProposalAlreadyRejectedException(id)
        }
        if (!user.isAdmin()) {
            throw MeetupGroupProposalCannotBeRejectedException(user.id)
        }
        if (rejectedReason.isNullOrBlank()) {
            throw MeetupGroupProposalRequireReasonException()
        }

        return MeetupGroupProposal(
            id,
            proposalUserId,
            name,
            description,
            location,
            proposalDate,
            status = MeetupGroupProposalStatus.REJECTED,
            rejectedReason = rejectedReason,
            events = mutableListOf<DomainEvent>(
                MeetupGroupProposalRejected(
                    id.value,
                    proposalUserId.value,
                    rejectedReason
                )
            )
        )
    }

}

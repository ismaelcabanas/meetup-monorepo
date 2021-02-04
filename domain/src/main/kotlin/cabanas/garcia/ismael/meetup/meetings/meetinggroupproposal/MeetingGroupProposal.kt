package cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingGroupProposal(
    val id: MeetingGroupProposalId,
    val proposalMemberId: MemberId,
    val name: String,
    val description: String,
    val meetingGroupLocation: MeetingGroupLocation,
    val proposalDate: Instant
) {
    private var events = mutableListOf<DomainEvent>()

    fun new(): MeetingGroupProposal {
        registerDomainEvent(
            MeetingGroupProposalCreated(
                id.value,
                proposalMemberId.value,
                name,
                description,
                meetingGroupLocation.country,
                meetingGroupLocation.city,
                proposalDate
            )
        )

        return this
    }

    fun events() = events

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}

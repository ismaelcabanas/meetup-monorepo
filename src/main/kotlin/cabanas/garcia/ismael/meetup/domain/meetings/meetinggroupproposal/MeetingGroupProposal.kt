package cabanas.garcia.ismael.meetup.domain.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroupId
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent
import java.time.Instant

data class MeetingGroupProposal(
    val id: MeetingGroupProposalId,
    val proposalMemberId: MemberId,
    val name: String,
    val description: String,
    val meetingGroupLocation: MeetingGroupLocation,
    val proposalDate: Instant
) {
    private lateinit var state: MeetingGroupProposalState
    private var events = mutableListOf<DomainEvent>()

    fun propose(): MeetingGroupProposal =
        this.also {
            this.state = MeetingGroupProposalState.PROPOSED

            registerDomainEvent(
                MeetingGroupProposalProposed(
                    id.value,
                    proposalMemberId.value,
                    name,
                    description,
                    meetingGroupLocation.country,
                    meetingGroupLocation.city,
                    proposalDate
                )
            )
        }

    fun accept(): MeetingGroupProposal {
        if (this.state == MeetingGroupProposalState.ACCEPTED) {
            throw MeetingGroupProposalAlreadyAcceptedException(this.id)
        }

        return this.also {
            this.state = MeetingGroupProposalState.ACCEPTED

            registerDomainEvent(
                MeetingGroupProposalAccepted(
                    this.id.value
                )
            )
        }
    }

    fun createMeetingGroup(creationDate: Instant): MeetingGroup =
        MeetingGroup(
            MeetingGroupId(id.value),
            proposalMemberId,
            name,
            description,
            meetingGroupLocation,
            creationDate
        ).create()

    fun events() = events

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}

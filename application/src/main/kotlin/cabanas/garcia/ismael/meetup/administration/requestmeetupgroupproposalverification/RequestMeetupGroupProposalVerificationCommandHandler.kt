package cabanas.garcia.ismael.meetup.administration.requestmeetupgroupproposalverification

import cabanas.garcia.ismael.meetup.administration.MeetupGroupProposalFactory
import cabanas.garcia.ismael.meetup.administration.MeetupGroupProposalRepository
import cabanas.garcia.ismael.meetup.shared.service.EventBus

class RequestMeetupGroupProposalVerificationCommandHandler(
    val repository: MeetupGroupProposalRepository,
    val eventBus: EventBus
) {
    fun handle(command: RequestMeetupGroupProposalVerificationCommand) {
        val meetupGroupProposal = MeetupGroupProposalFactory.createProposal(
            command.meetupGroupProposalId,
            command.proposalUserId,
            command.name,
            command.description,
            command.country,
            command.city,
            command.proposalDate
        )

        repository.save(meetupGroupProposal)

        eventBus.publish(meetupGroupProposal.events())
    }
}
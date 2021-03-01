package cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal

import cabanas.garcia.ismael.meetup.domain.administration.MeetupGroupProposalId
import cabanas.garcia.ismael.meetup.domain.administration.MeetupGroupProposalRepository
import cabanas.garcia.ismael.meetup.domain.administration.UserId
import cabanas.garcia.ismael.meetup.domain.shared.service.EventBus

class AcceptMeetupGroupProposalCommandHandler(
    private val repository: MeetupGroupProposalRepository,
    private val eventBus: EventBus
) {
    fun handle(command: AcceptMeetupGroupProposalCommand) {
        val meetupGroupProposal = repository.findBy(MeetupGroupProposalId(command.meetupGroupProposalId))
        val user = repository.findBy(UserId(command.userId))

        val meetupGroupProposalApproved = meetupGroupProposal.approve(user)

        repository.save(meetupGroupProposalApproved)
        eventBus.publish(meetupGroupProposalApproved.events())
    }

}
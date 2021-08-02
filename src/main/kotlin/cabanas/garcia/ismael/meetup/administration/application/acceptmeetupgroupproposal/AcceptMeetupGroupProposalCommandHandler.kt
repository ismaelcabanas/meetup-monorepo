package cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal

import cabanas.garcia.ismael.meetup.administration.domain.MeetupGroupProposalId
import cabanas.garcia.ismael.meetup.administration.domain.MeetupGroupProposalRepository
import cabanas.garcia.ismael.meetup.administration.domain.UserId
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus

class AcceptMeetupGroupProposalCommandHandler(
    private val repository: MeetupGroupProposalRepository,
    private val eventBus: EventBus
): CommandHandler<AcceptMeetupGroupProposalCommand> {
    override fun handle(command: AcceptMeetupGroupProposalCommand) {
        val meetupGroupProposal = repository.findBy(MeetupGroupProposalId(command.meetupGroupProposalId))
        val user = repository.findBy(UserId(command.userId))

        val meetupGroupProposalApproved = meetupGroupProposal.approve(user)

        repository.save(meetupGroupProposalApproved)
        eventBus.publish(meetupGroupProposalApproved.events())
    }

}

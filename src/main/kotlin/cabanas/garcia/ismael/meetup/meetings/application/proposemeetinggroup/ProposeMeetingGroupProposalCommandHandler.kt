package cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposal
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import cabanas.garcia.ismael.meetup.shared.application.InvalidCommandException
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus

class ProposeMeetingGroupProposalCommandHandler(
    private val meetingGroupProposalRepository: MeetingGroupProposalRepository,
    private val eventBus: EventBus
) : CommandHandler<ProposeMeetingGroupProposalCommand> {

    override fun handle(command: ProposeMeetingGroupProposalCommand) {
        if (command.meetingGroupProposalId == null) {
            throw InvalidCommandException("Meeting group proposal identifier should be set.")
        }

        val meetingGroupProposal =
            MeetingGroupProposal(
                MeetingGroupProposalId(command.meetingGroupProposalId!!),
                MemberId(command.proposalMemberId!!),
                command.meetingGroupProposalName!!,
                command.meetingGroupProposalDescription!!,
                MeetingGroupLocation(command.meetingGroupProposalCountry!!, command.meetingGroupProposalCity!!),
                command.meetingGroupProposalDate!!
            )

        meetingGroupProposal.propose()

        meetingGroupProposalRepository.save(meetingGroupProposal)
        eventBus.publish(meetingGroupProposal.pullEvents())
    }
}

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
        checkCommand(command)

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

    private fun checkCommand(command: ProposeMeetingGroupProposalCommand) {
        if (command.meetingGroupProposalId == null) {
            throw InvalidCommandException("Meeting group proposal identifier is required.")
        }
        if (command.proposalMemberId == null) {
            throw InvalidCommandException("Meeting group proposal member is required.")
        }
        if (command.meetingGroupProposalName == null) {
            throw InvalidCommandException("Meeting group proposal name is required.")
        }
        if (command.meetingGroupProposalDescription == null) {
            throw InvalidCommandException("Meeting group proposal description is required.")
        }
        if (command.meetingGroupProposalCountry == null) {
            throw InvalidCommandException("Meeting group proposal country is required.")
        }
        if (command.meetingGroupProposalCity == null) {
            throw InvalidCommandException("Meeting group proposal city is required.")
        }
        if (command.meetingGroupProposalDate == null) {
            throw InvalidCommandException("Meeting group proposal date is required.")
        }
    }
}

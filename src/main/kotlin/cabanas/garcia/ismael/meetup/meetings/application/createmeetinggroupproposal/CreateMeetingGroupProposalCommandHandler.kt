package cabanas.garcia.ismael.meetup.meetings.application.createmeetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposal
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalFactory
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler

class CreateMeetingGroupProposalCommandHandler(
    private val meetingGroupProposalRepository: MeetingGroupProposalRepository
) : CommandHandler<CreateMeetingGroupProposalCommand> {

    override fun handle(command: CreateMeetingGroupProposalCommand) {
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
    }
}

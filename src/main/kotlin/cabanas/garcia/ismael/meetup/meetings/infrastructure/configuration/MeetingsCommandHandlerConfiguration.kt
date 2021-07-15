package cabanas.garcia.ismael.meetup.meetings.infrastructure.configuration

import cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup.ProposeMeetingGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeetingsCommandHandlerConfiguration {
    @Bean
    fun createMeetingGroupProposalCommandHandler(
        meetingGroupProposalRepository: MeetingGroupProposalRepository
    ): ProposeMeetingGroupProposalCommandHandler =
        ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository)
}
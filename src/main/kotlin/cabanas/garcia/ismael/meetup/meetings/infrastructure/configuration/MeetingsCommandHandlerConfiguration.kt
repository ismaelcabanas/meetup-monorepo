package cabanas.garcia.ismael.meetup.meetings.infrastructure.configuration

import cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup.ProposeMeetingGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeetingsCommandHandlerConfiguration {
    @Bean
    fun createMeetingGroupProposalCommandHandler(
        meetingGroupProposalRepository: MeetingGroupProposalRepository,
        eventBus: EventBus
    ): ProposeMeetingGroupProposalCommandHandler =
        ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository, eventBus)
}
package cabanas.garcia.ismael.meetup.meetings.infrastructure.configuration

import cabanas.garcia.ismael.meetup.meetings.application.createmeetinggroupproposal.CreateMeetingGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeetingsCommandHandlerConfiguration {
    @Bean
    fun createMeetingGroupProposalCommandHandler(
        meetingGroupProposalRepository: MeetingGroupProposalRepository
    ): CreateMeetingGroupProposalCommandHandler =
        CreateMeetingGroupProposalCommandHandler(meetingGroupProposalRepository)
}
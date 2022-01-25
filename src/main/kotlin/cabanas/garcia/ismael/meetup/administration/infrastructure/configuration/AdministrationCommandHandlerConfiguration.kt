package cabanas.garcia.ismael.meetup.administration.infrastructure.configuration

import cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal.AcceptMeetupGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.administration.application.requestmeetupgroupproposalverification.RequestMeetupGroupProposalVerificationCommandHandler
import cabanas.garcia.ismael.meetup.administration.domain.MeetupGroupProposalRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdministrationCommandHandlerConfiguration {
    @Bean
    fun acceptMeetingGroupProposalCommandHandler(
        meetupGroupProposalRepository: MeetupGroupProposalRepository,
        eventBus: EventBus
    ): AcceptMeetupGroupProposalCommandHandler =
        AcceptMeetupGroupProposalCommandHandler(meetupGroupProposalRepository, eventBus)

    @Bean
    fun requestMeetupGroupProposalVerificationCommandHandler(
        repository: MeetupGroupProposalRepository,
        eventBus: EventBus
    ): RequestMeetupGroupProposalVerificationCommandHandler =
        RequestMeetupGroupProposalVerificationCommandHandler(repository, eventBus)
}
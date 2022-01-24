package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal.AcceptMeetupGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup.ProposeMeetingGroupProposalCommandHandler
import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommandHandler
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.shared.application.InMemoryCommandBus
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.application.createuser.CreateUserByUserRegistrationCommandHandler
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun dummyCommandBus(
        userRegistrationRepository: UserRegistrationRepository,
        usersCounter: UsersCounter,
        eventBus: EventBus,
        authenticateUserCommandHandler: AuthenticateUserCommandHandler,
        createUserByUserRegistrationCommandHandler: CreateUserByUserRegistrationCommandHandler,
        proposeMeetingGroupProposalCommandHandler: ProposeMeetingGroupProposalCommandHandler,
        acceptMeetupGroupProposalCommandHandler: AcceptMeetupGroupProposalCommandHandler,
        createPayerCommandHandler: CreatePayerCommandHandler
    ): CommandBus = InMemoryCommandBus(
        userRegistrationRepository,
        usersCounter,
        eventBus,
        authenticateUserCommandHandler,
        createUserByUserRegistrationCommandHandler,
        proposeMeetingGroupProposalCommandHandler,
        acceptMeetupGroupProposalCommandHandler,
        createPayerCommandHandler
    )
}
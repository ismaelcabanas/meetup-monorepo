package cabanas.garcia.ismael.meetup.meetings.infrastructure.configuration

import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposal
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeetingsRepositoryConfiguration {
    @Bean
    fun meetingGroupProposalRepository(): MeetingGroupProposalRepository =
        InMemoryMeetingGroupProposalRepository()
}

class InMemoryMeetingGroupProposalRepository :
    MeetingGroupProposalRepository {
    override fun save(meetingGroupProposal: MeetingGroupProposal) {
        TODO("Not yet implemented")
    }

}

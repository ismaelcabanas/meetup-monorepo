package cabanas.garcia.ismael.meetup.meetings.infrastructure.configuration

import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposal
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Configuration
class MeetingsRepositoryConfiguration {
    @Bean
    fun meetingGroupProposalRepository(): MeetingGroupProposalRepository =
        InMemoryMeetingGroupProposalRepository()
}

class InMemoryMeetingGroupProposalRepository : MeetingGroupProposalRepository {
    private var meetingGroupProposals: ConcurrentMap<MeetingGroupProposalId, MeetingGroupProposal> = ConcurrentHashMap()

    override fun save(meetingGroupProposal: MeetingGroupProposal) {
        meetingGroupProposals[meetingGroupProposal.id] = meetingGroupProposal
    }

}

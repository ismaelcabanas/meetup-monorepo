package cabanas.garcia.ismael.meetup.administration.infrastructure.configuration

import cabanas.garcia.ismael.meetup.administration.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Configuration
class AdministrationRepositoryConfiguration {
    @Bean
    fun meetupGroupProposalRepository(): MeetupGroupProposalRepository =
        InMemoryMeetupGroupProposalRepository()
}

class InMemoryMeetupGroupProposalRepository : MeetupGroupProposalRepository {
    private var meetingGroupProposals: ConcurrentMap<MeetupGroupProposalId, MeetupGroupProposal> = ConcurrentHashMap()
    override fun findBy(id: MeetupGroupProposalId): MeetupGroupProposal =
        meetingGroupProposals[id]!!

    override fun findBy(id: UserId): User {
        TODO("Not yet implemented")
    }

    override fun save(meetupGroupProposal: MeetupGroupProposal) {
        meetingGroupProposals[meetupGroupProposal.id] = meetupGroupProposal
    }

}

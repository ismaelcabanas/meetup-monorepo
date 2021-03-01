package cabanas.garcia.ismael.meetup.domain.administration

interface MeetupGroupProposalRepository {
    fun findBy(id: MeetupGroupProposalId): MeetupGroupProposal
    fun findBy(id: UserId): User
    fun save(meetupGroupProposal: MeetupGroupProposal)
}
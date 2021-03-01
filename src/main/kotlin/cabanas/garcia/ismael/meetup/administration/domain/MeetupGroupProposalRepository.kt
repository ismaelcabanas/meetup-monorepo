package cabanas.garcia.ismael.meetup.administration.domain

interface MeetupGroupProposalRepository {
    fun findBy(id: MeetupGroupProposalId): MeetupGroupProposal
    fun findBy(id: UserId): User
    fun save(meetupGroupProposal: MeetupGroupProposal)
}
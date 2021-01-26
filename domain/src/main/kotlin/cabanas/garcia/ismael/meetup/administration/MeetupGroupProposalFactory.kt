package cabanas.garcia.ismael.meetup.administration

import java.time.Instant

object MeetupGroupProposalFactory {
    fun createProposal(
        id: String,
        userId: String,
        name: String,
        description: String,
        country: String,
        city: String,
        date: Instant): MeetupGroupProposal {
        val meetupGroupProposal = MeetupGroupProposal(MeetupGroupProposalId(id), userId, name, description, MeetupLocation(country, city), date)

        return meetupGroupProposal.proposal()
    }

}
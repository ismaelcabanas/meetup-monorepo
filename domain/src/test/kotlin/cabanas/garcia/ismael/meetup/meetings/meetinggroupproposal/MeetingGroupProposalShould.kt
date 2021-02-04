package cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Test
import java.time.Instant

class MeetingGroupProposalShould {
    @Test
    fun `propose new meeting group`() {
        val meetingGroupProposal = MeetingGroupProposalFactory.propose(
            MeetingGroupProposalId(SOME_MEETING_GROUP_PROPOSAL_ID),
            MemberId(SOME_MEMBER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        )

        meetingGroupProposal.events() shouldContain
                MeetingGroupProposalCreated(
                    SOME_MEETING_GROUP_PROPOSAL_ID,
                    SOME_MEMBER_ID,
                    SOME_NAME,
                    SOME_DESCRIPTION,
                    SOME_COUNTRY,
                    SOME_CITY,
                    Instant.parse(SOME_DATE)
                )
    }

    private companion object {
        private const val SOME_MEETING_GROUP_PROPOSAL_ID = "some meeting group proposal id"
        private const val SOME_NAME = "some name"
        private const val SOME_DESCRIPTION = "some description"
        private const val SOME_COUNTRY = "some country"
        private const val SOME_CITY = "some city"
        private const val SOME_MEMBER_ID = "some member id"
        private const val SOME_DATE = "2021-01-25T13:13:03Z"
    }
}
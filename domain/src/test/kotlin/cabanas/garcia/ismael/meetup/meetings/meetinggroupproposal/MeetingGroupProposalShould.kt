package cabanas.garcia.ismael.meetup.meetings.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
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
                MeetingGroupProposalProposed(
                    SOME_MEETING_GROUP_PROPOSAL_ID,
                    SOME_MEMBER_ID,
                    SOME_NAME,
                    SOME_DESCRIPTION,
                    SOME_COUNTRY,
                    SOME_CITY,
                    Instant.parse(SOME_DATE)
                )
    }

    @Test
    fun `accept meeting group proposal`() {
        val meetingGroupProposalProposed = MeetingGroupProposalMother.proposed(
            SOME_MEETING_GROUP_PROPOSAL_ID
        )

        meetingGroupProposalProposed.accept()

        meetingGroupProposalProposed.events() shouldContain
                MeetingGroupProposalAccepted(
                    SOME_MEETING_GROUP_PROPOSAL_ID
                )
    }

    @Test
    fun `fail when accept meeting group proposal already accepted`() {
        val meetingGroupProposalAccepted = MeetingGroupProposalMother.accepted(
            SOME_MEETING_GROUP_PROPOSAL_ID
        )

        val exception = shouldThrow<MeetingGroupProposalAlreadyAcceptedException> {
            meetingGroupProposalAccepted.accept()
        }

        exception.message shouldBe "Meeting group proposal '$SOME_MEETING_GROUP_PROPOSAL_ID' already accepted."
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
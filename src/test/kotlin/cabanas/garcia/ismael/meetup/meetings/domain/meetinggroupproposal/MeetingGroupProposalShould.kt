package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupCreated
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.NewMeetingGroupMemberJoined
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
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

    @Test
    fun `create meeting group and creator meeting group proposal is the meeting group organizer`() {
        val meetingGroupProposalAccepted = MeetingGroupProposalFactory.propose(
            MeetingGroupProposalId(SOME_MEETING_GROUP_PROPOSAL_ID),
            MemberId(SOME_MEMBER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        ).accept()

        val meetupGroup = meetingGroupProposalAccepted.createMeetingGroup(
            Instant.parse(SOME_MEETING_GROUP_CREATION_DATE)
        )

        meetupGroup.events() shouldContainExactly
                mutableListOf(
                    MeetingGroupCreated(
                        SOME_MEETING_GROUP_PROPOSAL_ID,
                        SOME_MEMBER_ID,
                        SOME_NAME,
                        SOME_DESCRIPTION,
                        SOME_COUNTRY,
                        SOME_CITY,
                        Instant.parse(SOME_MEETING_GROUP_CREATION_DATE)
                    ),
                    NewMeetingGroupMemberJoined(
                        SOME_MEETING_GROUP_PROPOSAL_ID,
                        SOME_MEMBER_ID
                    )
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
        private const val SOME_MEETING_GROUP_CREATION_DATE = "2021-02-01T03:13:03Z"
    }
}
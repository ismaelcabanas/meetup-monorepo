package cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal

import cabanas.garcia.ismael.meetup.administration.domain.MeetupGroupProposalRepository
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.mockk.mockk
import org.junit.jupiter.api.Test

class AcceptMeetupGroupProposalShould {
    var repository = mockk<MeetupGroupProposalRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)

    @Test
    fun `accept a meetup group proposal`() {
        /*
        every { repository.findBy(UserId(SOME_USER_ID)) } returns User(UserId(SOME_USER_ID), "ADMIN")
        every { repository.findBy(MeetupGroupProposalId(SOME_MEETUP_GROUP_PROPOSAL_ID)) } returns MeetupGroupProposal(
            MeetupGroupProposalId(SOME_MEETUP_GROUP_PROPOSAL_ID),
            UserId(SOME_USER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetupGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        )
        val command = AcceptMeetupGroupProposalCommand(
            SOME_MEETUP_GROUP_PROPOSAL_ID,
            SOME_USER_ID
        )
        val commandHandler = AcceptMeetupGroupProposalCommandHandler(repository, eventBus)

        commandHandler.handle(command)

        verify {
            eventBus.publish(
                listOf(
                    MeetupGroupProposalApproved(
                        SOME_MEETUP_GROUP_PROPOSAL_ID,
                        SOME_USER_ID,
                        SOME_NAME,
                        SOME_DESCRIPTION,
                        SOME_COUNTRY,
                        SOME_CITY,
                        Instant.parse(SOME_DATE)
                    )
                )
            )
        }

         */
    }

    private companion object {
        private const val SOME_MEETUP_GROUP_PROPOSAL_ID = "some id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_COUNTRY = "some country"
        private const val SOME_CITY = "some city"
        private const val SOME_NAME = "some name"
        private const val SOME_DESCRIPTION = "some description"
        private const val SOME_DATE = "2021-01-25T13:13:03Z"
    }
}
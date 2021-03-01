package cabanas.garcia.ismael.meetup.administration.application.requestmeetupgroupproposalverification

import cabanas.garcia.ismael.meetup.domain.administration.*
import cabanas.garcia.ismael.meetup.domain.shared.service.EventBus
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.Instant

class RequestMeetupGroupProposalVerificationShould {
    var repository = mockk<MeetupGroupProposalRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)

    @Test
    fun `create meetup group proposal`() {
        val command = RequestMeetupGroupProposalVerificationCommand(
            SOME_MEETUP_GROUP_PROPOSAL_ID,
            SOME_USER_ID,
            SOME_NAME,
            SOME_DESCRIPTION,
            SOME_COUNTRY,
            SOME_CITY,
            Instant.parse(SOME_DATE)
        )
        val commandHandler = RequestMeetupGroupProposalVerificationCommandHandler(repository, eventBus)

        commandHandler.handle(command)

        verify {
            eventBus.publish(
                listOf(
                    MeetupGroupProposalCreated(
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
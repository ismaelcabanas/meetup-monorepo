package cabanas.garcia.ismael.meetup.administration.api.v1

import cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal.AcceptMeetupGroupProposalCommand
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalAlreadyAcceptedException
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalNotProposedException
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.shared.application.SuccessCommandBus
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.patch
import java.util.*

@WebMvcTest(PatchAcceptMeetingGroupProposalController::class)
class PatchAcceptMeetingGroupProposalControllerShould {
    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var adminMemberId: String

    @MockBean
    private lateinit var commandBus: CommandBus

    @BeforeEach
    fun `set up`() {
        adminMemberId = UUID.randomUUID().toString()
    }

    @Test
    fun `return 403 when header meeting group proposal member id is not set`() {
        mockMvc.patch("/v1/administration/meeting-group-proposals/${SOME_MEETING_GROUP_PROPOSAL_ID}/accept") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isForbidden() }
            content {
                jsonPath("$.message", equalTo("X-Meeting-User-Info header must be set."))
            }
        }
    }

    @Test
    fun `return 200 when patch accept meeting group proposal`() {
        mockMvc.patch("/v1/administration/meeting-group-proposals/${SOME_MEETING_GROUP_PROPOSAL_ID}/accept") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", adminMemberId)
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `return 409 when patch accept meeting group proposal already accepted`() {
        `when`(
            commandBus.dispatch(AcceptMeetupGroupProposalCommand(SOME_MEETING_GROUP_PROPOSAL_ID, adminMemberId))
        )
            .thenThrow(
                MeetingGroupProposalAlreadyAcceptedException(MeetingGroupProposalId(SOME_MEETING_GROUP_PROPOSAL_ID))
            )

        mockMvc.patch("/v1/administration/meeting-group-proposals/${SOME_MEETING_GROUP_PROPOSAL_ID}/accept") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", adminMemberId)
        }.andExpect {
            status { isConflict() }
            content {
                jsonPath("$.message", equalTo("Meeting group proposal already accepted."))
            }
        }
    }

    @Test
    fun `return 409 when patch accept meeting group proposal not proposed`() {
        `when`(
            commandBus.dispatch(AcceptMeetupGroupProposalCommand(SOME_MEETING_GROUP_PROPOSAL_ID, adminMemberId))
        )
            .thenThrow(
                MeetingGroupProposalNotProposedException(MeetingGroupProposalId(SOME_MEETING_GROUP_PROPOSAL_ID))
            )

        mockMvc.patch("/v1/administration/meeting-group-proposals/${SOME_MEETING_GROUP_PROPOSAL_ID}/accept") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", adminMemberId)
        }.andExpect {
            status { isConflict() }
            content {
                jsonPath("$.message", equalTo("Meeting group proposal not proposed."))
            }
        }
    }

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun commandBus() = SuccessCommandBus()
    }

    private companion object {
        private const val SOME_MEETING_GROUP_PROPOSAL_ID = "some meeting group proposal id"
    }
}
package cabanas.garcia.ismael.meetup.meetings.api.v1

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.*
import java.util.concurrent.TimeUnit

@WebMvcTest(PostMeetingGroupProposalController::class)
class PostMeetingGroupProposalControllerShould {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    private lateinit var proposalMemberId: String

    @BeforeEach
    fun `set up`() {
        proposalMemberId = UUID.randomUUID().toString()
    }

    @Test
    fun `return 400 when header meeting group proposal member id is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.random())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("X-Meeting-User-Info header must be set."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal identifier is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalId())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalId"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal identifier cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal name is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalName())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalName"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal name cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal description is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalDescription())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalDescription"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal description cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal city is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalCity())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalCity"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal city cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal country is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalCountry())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalCountry"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal country cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when meeting group proposal date is not set`() {
        mockMvc.post("/v1/meeting/meeting-group-proposals") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", proposalMemberId)
            content = mapper.writeValueAsString(PostMeetingGroupProposalRequestMother.withoutMeetingGroupProposalDate())
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", equalTo("Bad request data."))
                jsonPath("$.errors[0].field", equalTo("meetingGroupProposalDate"))
                jsonPath("$.errors[0].error", equalTo("Meeting group proposal date cannot be null."))
            }
        }
    }

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun commandBus() = mockk<CommandBus>()
    }
}

object PostMeetingGroupProposalRequestMother {
    fun withoutMeetingGroupProposalId(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )

    fun withoutMeetingGroupProposalName(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )

    fun withoutMeetingGroupProposalDescription(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )

    fun withoutMeetingGroupProposalCity(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )

    fun withoutMeetingGroupProposalCountry(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )

    fun withoutMeetingGroupProposalDate(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country()
        )

    fun random(): PostMeetingGroupProposalRequest =
        PostMeetingGroupProposalRequest(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCity = MotherCreator.faker().address().city(),
            meetingGroupProposalCountry = MotherCreator.faker().address().country(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()
        )
}

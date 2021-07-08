package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.meetings.api.v1.PostMeetingGroupProposalRequest
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant
import java.util.UUID
import java.util.concurrent.TimeUnit

@SpringBootTest
@AutoConfigureMockMvc
class ProposeMeetingGroupAcceptanceTest : BaseAcceptanceTest() {
    @Autowired
    private lateinit var mvc: MockMvc

    val userId = UUID.randomUUID().toString()
    val login: String = MotherCreator.faker().internet().emailAddress()
    val password: String = MotherCreator.faker().internet().password()

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
        givenUserRegisterWith(
            userId,
            MotherCreator.faker().name().firstName(),
            MotherCreator.faker().name().lastName(),
            login,
            password
        )
        andUserConfirmRegistration(userId)
    }

    @Test
    fun `meeting group user propose a meetup group`() {
        val meetingGroupProposalId = UUID.randomUUID().toString()
        val meetingGroupProposalName = MotherCreator.faker().space().galaxy()
        val meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence()
        val meetingGroupProposalCountry = MotherCreator.faker().address().country()
        val meetingGroupProposalCity = MotherCreator.faker().address().city()
        val meetingGroupProposalDate = MotherCreator.faker().date().future(60, TimeUnit.DAYS).toInstant()

        givenUserMakesMeetingGroupProposalWith(
            meetingGroupProposalId,
            meetingGroupProposalName,
            meetingGroupProposalDescription,
            meetingGroupProposalCountry,
            meetingGroupProposalCity,
            meetingGroupProposalDate
        )

        whenAdministratorAcceptMeetingGroupProposal(meetingGroupProposalId)

        thenUserMeetingGroupCanJoinToMeetingGroup(meetingGroupProposalId)
    }

    private fun givenUserMakesMeetingGroupProposalWith(
        meetingGroupProposalId: String?,
        meetingGroupProposalName: String?,
        meetingGroupProposalDescription: String?,
        meetingGroupProposalCountry: String?,
        meetingGroupProposalCity: String?,
        meetingGroupProposalDate: Instant?
    ) {
        val requestBody = PostMeetingGroupProposalRequest(
            meetingGroupProposalId,
            meetingGroupProposalName,
            meetingGroupProposalDescription,
            meetingGroupProposalCountry,
            meetingGroupProposalCity,
            meetingGroupProposalDate
        )
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/meeting/meeting-group-proposals")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isCreated)
    }

    private fun whenAdministratorAcceptMeetingGroupProposal(meetingGroupProposalId: String) {
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/administration/meeting-group-proposals/$meetingGroupProposalId/accept")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
    }

    private fun thenUserMeetingGroupCanJoinToMeetingGroup(meetingGroupProposalId: String) {
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/meeting/meeting-groups/$meetingGroupProposalId/join")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
    }
}

package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.useraccess.api.v1.AuthenticateRequestBody
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import java.util.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class SignInAcceptanceTest : BaseAcceptanceTest() {
    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `sign in user`() {
        val userId = UUID.randomUUID().toString()
        val login = MotherCreator.faker().internet().emailAddress()
        val password = MotherCreator.faker().internet().password()

        givenUserRegisterWith(
            userId,
            MotherCreator.faker().name().firstName(),
            MotherCreator.faker().name().lastName(),
            login,
            password
        )

        whenUserConfirmRegistration(userId)

        thenUserCanSignIn(login, password)
    }

    private fun thenUserCanSignIn(login: String, password: String) {
        val requestBody = AuthenticateRequestBody(login, password)

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/users")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
    }

}
package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.useraccess.api.v1.AuthenticateRequestBody
import cabanas.garcia.ismael.meetup.useraccess.api.v1.CreateUserRegistrationRequest
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
class SignInAcceptanceTest {
    @Autowired
    private lateinit var mvc: MockMvc

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

    private fun givenUserRegisterWith(
        userId: String,
        firstName: String,
        lastName: String,
        login: String,
        password: String
    ) {
        val requestBody = CreateUserRegistrationRequest(
            userId,
            firstName,
            lastName,
            login,
            password
        )
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/user-registrations")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isCreated)
    }

    private fun whenUserConfirmRegistration(userId: String) {
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/user-registrations/${userId}/confirm")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
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
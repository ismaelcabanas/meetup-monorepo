package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.helper.DockerComposeHelper
import cabanas.garcia.ismael.meetup.useraccess.api.v1.CreateUserRegistrationRequest
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import java.time.ZoneOffset
import java.util.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
abstract class BaseAcceptanceTest {
    @Autowired
    protected lateinit var mvc: MockMvc

    companion object {

        private val dockerCompose = DockerComposeHelper()

        @BeforeAll
        @JvmStatic
        fun dockerComposeUp() {
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
            dockerCompose.start()
        }

        @AfterAll
        @JvmStatic
        fun dockerComposeDown() {
            dockerCompose.stop()
        }
    }

    protected fun givenUserRegisterWith(
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

    protected fun andUserConfirmRegistration(userId: String) {
        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/user-registrations/${userId}/confirm")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)
    }

    protected fun whenUserConfirmRegistration(userId: String) {
        andUserConfirmRegistration(userId)
    }
}
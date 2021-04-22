package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.Application
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PostUserRegistrationController::class)
@ContextConfiguration(classes = [Application::class])
class PostUserRegistrationControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `return 201 when post a user registration`() {
        val requestBody = CreateUserRegistrationRequestMother.random()

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/user-registrations")
            .then()
            .log().all()
            .assertThat(status().isCreated)
    }
}
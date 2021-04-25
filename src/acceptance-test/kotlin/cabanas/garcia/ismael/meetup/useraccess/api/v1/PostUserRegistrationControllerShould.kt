package cabanas.garcia.ismael.meetup.useraccess.api.v1

import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PostUserRegistrationControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

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

        userRegistrationWasPersistedWith(
            requestBody.id,
            requestBody.firstName,
            requestBody.lastName,
            requestBody.email,
            requestBody.password
        )
    }

    private fun userRegistrationWasPersistedWith(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        val rowNum = JdbcTestUtils.countRowsInTableWhere(
            jdbcTemplate,
            "USER_REGISTRATIONS",
            "id='$id' AND firstName='$firstName' AND lastName='$lastName' AND email='$email' AND passwrod='$password'"
        )
        assertThat(rowNum).isEqualTo(1)
    }
}
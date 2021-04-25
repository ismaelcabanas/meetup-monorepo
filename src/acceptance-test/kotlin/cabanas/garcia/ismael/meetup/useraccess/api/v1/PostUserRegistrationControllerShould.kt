package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.*
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PostUserRegistrationControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    @MockBean
    private lateinit var usersCounter: UsersCounter

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

        verify { userRegistrationRepository
            .save(
                UserRegistrationFactory.registerNewUser(
                    requestBody.id,
                    requestBody.email,
                    requestBody.password,
                    requestBody.email,
                    requestBody.firstName,
                    requestBody.lastName,
                    usersCounter
                )
            )
        }
    }
}
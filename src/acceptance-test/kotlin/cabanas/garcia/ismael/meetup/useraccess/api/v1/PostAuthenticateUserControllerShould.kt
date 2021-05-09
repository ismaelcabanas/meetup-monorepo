package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.User
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserAuthenticated
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class PostAuthenticateUserControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean(relaxed = true)
    private lateinit var userRepository: UserRepository

    @MockkBean(relaxed = true)
    private lateinit var eventBus: EventBus

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `returns 200 when user authenticates succesfully`() {
        val requestBody = AuthenticationRequestMother.random()
        every { userRepository.findBy(requestBody.username, requestBody.password) } returns
                User(
                    UUID.randomUUID().toString(),
                    requestBody.username,
                    requestBody.password,
                    requestBody.username,
                    MotherCreator.faker().name().firstName(),
                    MotherCreator.faker().name().lastName()
                )

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/users")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)

        verify {
            userRepository.findBy(requestBody.username, requestBody.password)
        }

        verify {
            eventBus.publish(
                match {
                    it.contains(
                        UserAuthenticated(requestBody.username, requestBody.password)
                    )
                }
            )
        }
    }
}
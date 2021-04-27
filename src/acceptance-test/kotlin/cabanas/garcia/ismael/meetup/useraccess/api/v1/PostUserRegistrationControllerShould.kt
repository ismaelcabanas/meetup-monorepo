package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.NewUserRegistered
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PostUserRegistrationControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    @MockkBean
    private lateinit var eventBus: EventBus

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `return 201 when post a user registration`() {
        every { userRegistrationRepository.save(any()) } returns Unit
        every { eventBus.publish(any()) } returns Unit
        val requestBody = CreateUserRegistrationRequestMother.random()

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/v1/user-registrations")
            .then()
            .log().all()
            .assertThat(status().isCreated)

        verify {
            userRegistrationRepository.save(
                withArg {
                    assertThat(requestBody.id).isEqualTo(it.id.value)
                    assertThat(requestBody.firstName).isEqualTo(it.firstName)
                    assertThat(requestBody.lastName).isEqualTo(it.lastName)
                    assertThat(requestBody.email).isEqualTo(it.email)
                    assertThat(requestBody.password).isEqualTo(it.password)
                }
            )
        }

        verify {
            eventBus.publish(
                match {
                    it.contains(
                        NewUserRegistered(
                            requestBody.id,
                            requestBody.email,
                            requestBody.email,
                            requestBody.firstName,
                            requestBody.lastName
                        )
                    )
                }
            )
        }
    }
}
package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.*
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class PatchConfirmUserRegistrationControllerShould {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean(relaxed = true)
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    @MockkBean(relaxed = true)
    private lateinit var eventBus: EventBus

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `return 200 when confirm an user registration`() {
        val userRegistration = UserRegistrationMother.aUserRegistrationRegistered()
        every {
            userRegistrationRepository.findBy(
                userRegistration.id
            )
        } returns userRegistration

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/v1/user-registrations/${userRegistration.id.value}/confirm")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isOk)

        verify {
            userRegistrationRepository.save(
                withArg {
                    assertThat(userRegistration.id).isEqualTo(it.id)
                }
            )
        }

        verify {
            eventBus.publish(
                match {
                    it.contains(
                        UserRegistrationConfirmed(
                            userRegistration.id.value
                        )
                    )
                }
            )
        }
    }
}
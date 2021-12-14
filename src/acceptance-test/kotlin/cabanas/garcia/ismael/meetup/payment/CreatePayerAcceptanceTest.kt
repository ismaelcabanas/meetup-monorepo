package cabanas.garcia.ismael.meetup.payment

import cabanas.garcia.ismael.meetup.BaseAcceptanceTest
import cabanas.garcia.ismael.meetup.payment.domain.event.PayerCreated
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.mockk.verify
import io.restassured.module.mockmvc.RestAssuredMockMvc
import java.util.UUID
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class CreatePayerAcceptanceTest : BaseAcceptanceTest() {
    @Autowired private lateinit var mvc: MockMvc
    @Autowired private lateinit var jdbcTemplate: JdbcTemplate

    @MockBean private lateinit var eventBus: EventBus

    val userId = UUID.randomUUID().toString()
    val login: String = MotherCreator.faker().internet().emailAddress()
    val password: String = MotherCreator.faker().internet().password()
    val firstName: String = MotherCreator.faker().name().firstName()
    val lastName: String = MotherCreator.faker().name().lastName()

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `create a payer when user is registered successfully`() {
        givenUserRegisterWith(
            userId,
            firstName,
            lastName,
            login,
            password
        )

        thenPayerIsCreatedSuccessfully(userId)
        verify {
            eventBus.publish(
                listOf(
                    PayerCreated(
                        userId,
                        firstName,
                        lastName
                    )
                )
            )
        }
    }

    private fun thenPayerIsCreatedSuccessfully(userId: String) {
        JdbcTestUtils.countRowsInTableWhere(
            jdbcTemplate,
            "PAYERS",
            """
                ID = '${userId}'                
            """.trimIndent()
        ).let {
            Assertions.assertThat(it).isEqualTo(1)
        }
    }
}
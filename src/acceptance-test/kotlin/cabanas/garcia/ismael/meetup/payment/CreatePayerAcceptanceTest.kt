package cabanas.garcia.ismael.meetup.payment

import cabanas.garcia.ismael.meetup.BaseAcceptanceTest
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import io.restassured.module.mockmvc.RestAssuredMockMvc
import java.util.*
import javax.sql.DataSource
import org.assertj.db.api.Assertions.assertThat
import org.assertj.db.type.Table
import org.awaitility.kotlin.await
import org.awaitility.kotlin.untilAsserted
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc


@SpringBootTest
@AutoConfigureMockMvc
class CreatePayerAcceptanceTest : BaseAcceptanceTest() {
    @Autowired private lateinit var mvc: MockMvc
    @Autowired private lateinit var dataSource: DataSource

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

        thenPayerIsCreatedSuccessfully(userId, firstName, lastName, login)
    }

    private fun thenPayerIsCreatedSuccessfully(userId: String, firstName: String, lastName: String, login: String) {
        val table = Table(dataSource, "PAYERS")
        await untilAsserted {
            assertThat(table).row(0)
                .column("ID").value().isEqualTo(userId)
                .column("LOGIN").value().isEqualTo(login)
                .column("EMAIL").value().isEqualTo(login)
                .column("FIRST_NAME").value().isEqualTo(firstName)
                .column("LAST_NAME").value().isEqualTo(lastName)
        }
    }
}
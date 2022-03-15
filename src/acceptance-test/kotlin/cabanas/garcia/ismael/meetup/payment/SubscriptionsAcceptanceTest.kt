package cabanas.garcia.ismael.meetup.payment

import cabanas.garcia.ismael.meetup.BaseAcceptanceTest
import cabanas.garcia.ismael.meetup.payment.api.v1.BuySubscriptionRequest
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import javax.sql.DataSource
import org.assertj.db.api.Assertions
import org.assertj.db.type.Table
import org.awaitility.kotlin.await
import org.awaitility.kotlin.untilAsserted
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionsAcceptanceTest : BaseAcceptanceTest() {
    @Autowired
    private lateinit var dataSource: DataSource

    val userId = UUID.randomUUID().toString()
    val login: String = MotherCreator.faker().internet().emailAddress()
    val password: String = MotherCreator.faker().internet().password()

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
        givenUserRegisterWith(
            userId,
            MotherCreator.faker().name().firstName(),
            MotherCreator.faker().name().lastName(),
            login,
            password
        )
        andUserConfirmRegistration(userId)
    }

    @Test
    fun `GIVEN a member logged WHEN buys a subscription THEN a subscription is started successfully`() {
        val subscriptionPaymentData = SubscriptionPaymentData(
            paymentId = MotherCreator.faker().internet().uuid(),
            payerId = userId,
            type = "STANDARD",
            price = MotherCreator.faker().number().randomDouble(2, 1, 100),
            period = "MONTHLY",
            date = MotherCreator.faker().date().future(10, TimeUnit.DAYS).toInstant()
        )

        whenMemberBuysSubscription(subscriptionPaymentData)

        thenSubscriptionPaymentIsCreatedSuccessfully(subscriptionPaymentData)
    }

    private fun whenMemberBuysSubscription(data: SubscriptionPaymentData) {
        val buySubscriptionRequest = BuySubscriptionRequest(
            data.paymentId,
            data.type,
            data.price,
            data.period,
            data.date
        )

        RestAssuredMockMvc.given()
            .contentType(ContentType.JSON)
            .header("X-Meeting-User-Info", data.payerId)
            .body(buySubscriptionRequest)
            .`when`()
            .post("/v1/payment/subscriptions")
            .then()
            .log().all()
            .assertThat(MockMvcResultMatchers.status().isCreated)
    }

    private fun thenSubscriptionPaymentIsCreatedSuccessfully(data: SubscriptionPaymentData) {
        val table = Table(dataSource, "SUBSCRIPTION_PAYMENTS")
        await untilAsserted {
            Assertions.assertThat(table).row(0)
                .column("ID").value().isEqualTo(data.paymentId)
                .column("PAYER_ID").value().isEqualTo(data.payerId)
                .column("TYPE").value().isEqualTo(data.type)
                .column("PERIOD").value().isEqualTo(data.period)
                .column("START_DATE").value().isEqualTo(data.date)
                .column("MONEY_VALUE").value().isEqualTo(data.price)
                .column("STATUS").value().isEqualTo("WAITING_FOR_PAYMENT")
        }
    }
}

data class SubscriptionPaymentData(
    val paymentId: String,
    val payerId: String,
    val type: String,
    val price: Double,
    val period: String,
    val date: Instant
)

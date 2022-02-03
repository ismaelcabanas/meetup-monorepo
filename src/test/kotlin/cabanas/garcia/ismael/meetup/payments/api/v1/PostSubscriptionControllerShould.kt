package cabanas.garcia.ismael.meetup.payments.api.v1

import cabanas.garcia.ismael.meetup.payment.api.v1.PostSubscriptionController
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(PostSubscriptionController::class)
class PostSubscriptionControllerShould {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    private lateinit var memberId: String

    @BeforeEach
    fun `set up`() {
        memberId = UUID.randomUUID().toString()
    }

    @Test
    fun `return 201 when member buys a subscription`() {
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(BuySubscriptionRequestMother.random())
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `return 403 when header member id is not set`() {
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(BuySubscriptionRequestMother.random())
        }.andExpect {
            status { isForbidden() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("X-Meeting-User-Info header must be set."))
            }
        }
    }

    @Test
    fun `return 400 when subscriptions payment identifier is not set`() {
        val buySubscriptionRequestWithoutIdentifier = BuySubscriptionRequestMother.random(
            paymentId = null
        )
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(buySubscriptionRequestWithoutIdentifier)
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("Bad request data."))
                jsonPath("$.errors[0].field", CoreMatchers.equalTo("paymentId"))
                jsonPath("$.errors[0].error", CoreMatchers.equalTo("Payment identifier cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when subscriptions type is not set`() {
        val buySubscriptionRequestWithoutSubscriptionType = BuySubscriptionRequestMother.random(
            type = null
        )
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(buySubscriptionRequestWithoutSubscriptionType)
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("Bad request data."))
                jsonPath("$.errors[0].field", CoreMatchers.equalTo("type"))
                jsonPath("$.errors[0].error", CoreMatchers.equalTo("Subscription type cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when subscriptions value is not set`() {
        val buySubscriptionRequestWithoutSubscriptionValue = BuySubscriptionRequestMother.random(
            value = null
        )
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(buySubscriptionRequestWithoutSubscriptionValue)
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("Bad request data."))
                jsonPath("$.errors[0].field", CoreMatchers.equalTo("value"))
                jsonPath("$.errors[0].error", CoreMatchers.equalTo("Subscription value cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when subscriptions period is not set`() {
        val buySubscriptionRequestWithoutSubscriptionPeriod = BuySubscriptionRequestMother.random(
            period = null
        )
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(buySubscriptionRequestWithoutSubscriptionPeriod)
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("Bad request data."))
                jsonPath("$.errors[0].field", CoreMatchers.equalTo("period"))
                jsonPath("$.errors[0].error", CoreMatchers.equalTo("Subscription period cannot be null."))
            }
        }
    }

    @Test
    fun `return 400 when subscriptions date is not set`() {
        val buySubscriptionRequestWithoutSubscriptionDate = BuySubscriptionRequestMother.random(
            date = null
        )
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            header("X-Meeting-User-Info", memberId)
            content = mapper.writeValueAsString(buySubscriptionRequestWithoutSubscriptionDate)
        }.andExpect {
            status { isBadRequest() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("Bad request data."))
                jsonPath("$.errors[0].field", CoreMatchers.equalTo("date"))
                jsonPath("$.errors[0].error", CoreMatchers.equalTo("Subscription date cannot be null."))
            }
        }
    }

}
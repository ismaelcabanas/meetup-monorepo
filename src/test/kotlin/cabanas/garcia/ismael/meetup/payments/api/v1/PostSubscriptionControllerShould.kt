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
            content = mapper.writeValueAsString(BuySubscriptionRequestMother.randomStandardMonth())
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `return 403 when header member id is not set`() {
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(BuySubscriptionRequestMother.randomStandardMonth())
        }.andExpect {
            status { isForbidden() }
            content {
                jsonPath("$.message", CoreMatchers.equalTo("X-Meeting-User-Info header must be set."))
            }
        }
    }
}
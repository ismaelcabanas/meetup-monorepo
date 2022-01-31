package cabanas.garcia.ismael.meetup.payments.api.v1

import cabanas.garcia.ismael.meetup.payment.api.v1.PostSubscriptionController
import com.fasterxml.jackson.databind.ObjectMapper
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

    @Test
    fun `return 201 when member buys a subscription`() {
        mockMvc.post("/v1/payment/subscriptions") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(BuySubscriptionRequestMother.randomStandardMonth())
        }.andExpect {
            status { isCreated() }
        }
    }
}
package cabanas.garcia.ismael.meetup.payment.api.v1

import java.time.Instant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostSubscriptionController {
    @RequestMapping("/v1/payment/subscriptions")
    @PostMapping
    fun execute(
        @RequestHeader("X-Meeting-User-Info") memberId: String,
        @RequestBody requestBody: BuySubscriptionRequest
    ): ResponseEntity<Void> =
        ResponseEntity(HttpStatus.CREATED)
}

data class BuySubscriptionRequest(
    val paymentId: String,
    val type: String,
    val value: Double,
    val period: String,
    val date: Instant
)
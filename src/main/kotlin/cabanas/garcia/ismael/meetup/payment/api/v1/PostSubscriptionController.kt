package cabanas.garcia.ismael.meetup.payment.api.v1

import java.time.Instant
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
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
        @Valid @RequestBody requestBody: BuySubscriptionRequest
    ): ResponseEntity<Void> =
        ResponseEntity(HttpStatus.CREATED)
}

data class BuySubscriptionRequest(
    @get:NotEmpty(message = "Payment identifier cannot be null.")
    val paymentId: String? = null,
    @get:NotEmpty(message = "Subscription type cannot be null.")
    val type: String? = null,
    @get:NotNull(message = "Subscription value cannot be null.")
    val value: Double? = null,
    @get:NotEmpty(message = "Subscription period cannot be null.")
    val period: String? = null,
    @get:NotNull(message = "Subscription date cannot be null.")
    val date: Instant? = null
)
package cabanas.garcia.ismael.meetup.payment.api.v1

import cabanas.garcia.ismael.meetup.payment.application.buysubscription.BuySubscriptionCommand
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
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
class PostSubscriptionController(
    private val commandBus: CommandBus
) {
    @RequestMapping("/v1/payment/subscriptions")
    @PostMapping
    fun execute(
        @RequestHeader("X-Meeting-User-Info") memberId: String,
        @Valid @RequestBody requestBody: BuySubscriptionRequest
    ): ResponseEntity<Void> =
        requestBody.toCommand(memberId).let {
            commandBus.dispatch(it)
            ResponseEntity(HttpStatus.CREATED)
        }
}

private fun BuySubscriptionRequest.toCommand(memberId: String): BuySubscriptionCommand =
    BuySubscriptionCommand(
        paymentId = this.paymentId!!,
        payerId = memberId,
        type = this.type!!,
        value = this.value!!,
        period = this.period!!,
        date = this.date!!
    )

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
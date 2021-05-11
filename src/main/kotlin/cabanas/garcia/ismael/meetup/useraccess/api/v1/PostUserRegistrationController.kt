package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.application.newregistration.NewUserRegistrationCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostUserRegistrationController(
    private val commandBus: CommandBus
) {
    @RequestMapping("/v1/user-registrations")
    @PostMapping
    fun execute(@RequestBody requestBody: CreateUserRegistrationRequest): ResponseEntity<Void> =
        commandBus
            .dispatch(toCommand(requestBody))
            .let {
                ResponseEntity(HttpStatus.CREATED)
            }

    private fun toCommand(requestBody: CreateUserRegistrationRequest): NewUserRegistrationCommand =
        NewUserRegistrationCommand(
            requestBody.id,
            requestBody.firstName,
            requestBody.lastName,
            requestBody.email,
            requestBody.password
        )
}

data class CreateUserRegistrationRequest(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
package cabanas.garcia.ismael.meetup.useraccess.api.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostUserRegistrationController {
    @RequestMapping("/v1/user-registrations")
    @PostMapping
    fun execute(): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.CREATED)
    }
}

data class CreateUserRegistrationRequest(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
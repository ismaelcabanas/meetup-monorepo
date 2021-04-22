package cabanas.garcia.ismael.meetup.useraccess.api.v1

class PostUserRegistrationController {
}

data class CreateUserRegistrationRequest(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
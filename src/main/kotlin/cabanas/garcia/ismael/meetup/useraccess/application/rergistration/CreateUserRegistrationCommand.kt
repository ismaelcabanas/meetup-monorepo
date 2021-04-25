package cabanas.garcia.ismael.meetup.useraccess.application.rergistration

data class CreateUserRegistrationCommand(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

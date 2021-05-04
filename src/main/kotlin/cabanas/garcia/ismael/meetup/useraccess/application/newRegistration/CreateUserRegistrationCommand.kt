package cabanas.garcia.ismael.meetup.useraccess.application.newRegistration

import cabanas.garcia.ismael.meetup.shared.application.Command

data class CreateUserRegistrationCommand(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
): Command

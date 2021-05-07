package cabanas.garcia.ismael.meetup.useraccess.application.createuser

import cabanas.garcia.ismael.meetup.shared.application.Command

data class CreateUserByUserRegistrationCommand(
    val userRegistrationId: String
) : Command
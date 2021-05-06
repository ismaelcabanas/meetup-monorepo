package cabanas.garcia.ismael.meetup.useraccess.application.authentication

import cabanas.garcia.ismael.meetup.shared.application.Command

data class AuthenticateUserCommand(val login: String, val password: String) : Command

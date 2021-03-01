package cabanas.garcia.ismael.meetup.domain.useraccess.userregistration

class UserRegistrationAlreadyConfirmedException(val login: String)
    : Exception("$login has already confirmed the registration.") {
}
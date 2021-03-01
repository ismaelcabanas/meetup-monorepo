package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

class UserRegistrationAlreadyConfirmedException(val login: String)
    : Exception("$login has already confirmed the registration.") {
}
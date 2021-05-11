package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

object UserRegistrationFactory {
    fun create(
        id: String,
        login: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ): UserRegistration =
        UserRegistration(UserRegistrationId(id), login, password, email, firstName, lastName)
}

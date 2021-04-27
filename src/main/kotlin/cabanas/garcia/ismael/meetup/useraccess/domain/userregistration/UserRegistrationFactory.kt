package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

object UserRegistrationFactory {
    fun registerNewUser(
        id: String,
        login: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String,
        usersCounter: UsersCounter
    ): UserRegistration {
        val userRegistration = UserRegistration(UserRegistrationId(id), login, password, email, firstName, lastName)

        userRegistration.register(usersCounter)

        return userRegistration
    }
}

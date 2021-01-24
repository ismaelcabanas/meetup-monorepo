package cabanas.garcia.ismael.meetup.useraccess.user

object UserFactory {
    fun createUserFromUserRegistration(
        userId: String,
        login: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ): User =
        User(userId, login, password, email, firstName, lastName)
}
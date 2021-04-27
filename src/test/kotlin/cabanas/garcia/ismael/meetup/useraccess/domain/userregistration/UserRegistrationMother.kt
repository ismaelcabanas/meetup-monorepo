package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

object UserRegistrationMother {

    private const val SOME_USER_REGISTRATION_ID = "some id"
    private const val SOME_LOGIN = "some login"
    private const val SOME_PASSWORD = "some password"
    private const val SOME_EMAIL = "some email"
    private const val SOME_FIRST_NAME = "some first name"
    private const val SOME_LAST_NAME = "some last name"

    fun aUserRegistrationRegistered(): UserRegistration {
        val userRegistration = UserRegistration(
            UserRegistrationId(SOME_USER_REGISTRATION_ID),
            SOME_LOGIN,
            SOME_PASSWORD,
            SOME_EMAIL,
            SOME_FIRST_NAME,
            SOME_LAST_NAME
        )

        userRegistration.register(UsersCounterThatAlwaysReturnZero())

        return userRegistration
    }

    fun aUserRegistrationConfirmed(): UserRegistration {
        val userRegistrationRegistered = aUserRegistrationRegistered()

        userRegistrationRegistered.confirm()

        return userRegistrationRegistered
    }

    fun aUserRegistrationExpired(): UserRegistration {
        val userRegistrationRegistered = aUserRegistrationRegistered()

        userRegistrationRegistered.expire()

        return userRegistrationRegistered
    }

}

class UsersCounterThatAlwaysReturnZero : UsersCounter {
    override fun countUsersByLogin(login: String): Int = 0

}

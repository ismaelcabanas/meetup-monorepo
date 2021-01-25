package cabanas.garcia.ismael.meetup.useraccess.user

object UserMother {
    private const val SOME_USER_ID = "some id"
    private const val SOME_LOGIN = "some login"
    private const val SOME_PASSWORD = "some password"
    private const val SOME_EMAIL = "some email"
    private const val SOME_FIRST_NAME = "some first name"
    private const val SOME_LAST_NAME = "some last name"

    fun aUserWithoutRoles(): User =
        User(
            SOME_USER_ID,
            SOME_LOGIN,
            SOME_PASSWORD,
            SOME_EMAIL,
            SOME_FIRST_NAME,
            SOME_LAST_NAME
        )
}
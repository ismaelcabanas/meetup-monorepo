package cabanas.garcia.ismael.meetup.domain.administration

object UserMother {
    private const val SOME_ADMIN_USER_ID = "some admin id"
    private const val SOME_MEMBER_USER_ID = "some member id"

    fun aAdminUser(): User = User(UserId(SOME_ADMIN_USER_ID), "ADMIN")
    fun aMemberUser(): User = User(UserId(SOME_MEMBER_USER_ID), "MEMBER")
}
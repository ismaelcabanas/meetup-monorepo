package cabanas.garcia.ismael.meetup.domain.useraccess.userregistration

interface UsersCounter {
    fun countUsersByLogin(login: String): Int
}
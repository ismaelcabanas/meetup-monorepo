package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

interface UsersCounter {
    fun countUsersByLogin(login: String): Int
}
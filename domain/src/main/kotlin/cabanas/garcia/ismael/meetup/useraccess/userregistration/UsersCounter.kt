package cabanas.garcia.ismael.meetup.useraccess.userregistration

interface UsersCounter {
    fun countUsersByLogin(login: String): Int
}
package cabanas.garcia.ismael.meetup.useraccess.domain.user

interface UserRepository {
    fun findBy(login: String, password: String): User
    fun save(user: User)
}
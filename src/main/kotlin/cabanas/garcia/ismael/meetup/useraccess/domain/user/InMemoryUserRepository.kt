package cabanas.garcia.ismael.meetup.useraccess.domain.user

class InMemoryUserRepository : UserRepository {
    override fun findBy(login: String, password: String): User {
        TODO("Not yet implemented")
    }
}
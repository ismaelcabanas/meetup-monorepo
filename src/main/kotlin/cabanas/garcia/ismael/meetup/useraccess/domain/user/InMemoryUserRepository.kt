package cabanas.garcia.ismael.meetup.useraccess.domain.user

import java.lang.RuntimeException

class InMemoryUserRepository : UserRepository {
    private var users: MutableMap<UserId, User> = mutableMapOf()

    override fun findBy(login: String, password: String): User =
        users.values.find {
                user -> user.login == login && user.password == password
        } ?: throw RuntimeException("User not found")
}
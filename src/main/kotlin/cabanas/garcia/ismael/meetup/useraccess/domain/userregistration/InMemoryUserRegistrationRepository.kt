package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import java.lang.RuntimeException

class InMemoryUserRegistrationRepository : UserRegistrationRepository {
    private var userRegistrations: MutableMap<UserRegistrationId, UserRegistration> = mutableMapOf()
    override fun save(userRegistration: UserRegistration) {
        userRegistrations[userRegistration.id] = userRegistration
    }

    override fun findBy(id: UserRegistrationId): UserRegistration =
        userRegistrations[id] ?: throw RuntimeException("User registration not found")

}
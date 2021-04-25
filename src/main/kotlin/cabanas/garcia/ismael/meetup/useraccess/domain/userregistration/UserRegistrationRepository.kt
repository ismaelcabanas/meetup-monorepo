package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

interface UserRegistrationRepository {
    fun save(userRegistration: UserRegistration)
}
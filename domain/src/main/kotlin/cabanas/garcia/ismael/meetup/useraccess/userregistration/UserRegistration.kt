package cabanas.garcia.ismael.meetup.useraccess.userregistration

import cabanas.garcia.ismael.meetup.useraccess.user.User
import cabanas.garcia.ismael.meetup.useraccess.user.UserFactory
import java.time.Instant

data class UserRegistration (
    val id: UserRegistrationId,
    val login: String,
    val password: String,
    val email: String,
    val firstName:String,
    val lastName: String,
    val status: UserRegistrationStatus = UserRegistrationStatus.WAITING_FOR_CONFIRMATION,
    val events: List<DomainEvent> = emptyList(),
    val registerDate: Instant? = null
) {
    fun register(usersCounter: UsersCounter) =
        if (usersCounter.countUsersByLogin(this.login) == 0)
            UserRegistration(
                this.id,
                this.login,
                this.password,
                this.email,
                this.firstName,
                this.lastName,
                events = mutableListOf(NewUserRegistered(id.value, login, email, firstName, lastName)),
                registerDate = Instant.now()
            )
        else throw UserRegistrationAlreadyExistException(this.login)

    fun id() = id.value

    fun events() = events.toList()

    fun confirm(): UserRegistration {
        if (this.status == UserRegistrationStatus.CONFIRMED) {
            throw UserRegistrationAlreadyConfirmedException(this.login)
        } else if (this.status == UserRegistrationStatus.EXPIRED) {
            throw UserRegistrationAlreadyExpiredException()
        }

        return UserRegistration(
            this.id,
            this.login,
            this.password,
            this.email,
            this.firstName,
            this.lastName,
            status = UserRegistrationStatus.CONFIRMED,
            events = mutableListOf(UserRegistrationConfirmed(this.id.value)),
            registerDate = this.registerDate
        )
    }

    fun expire(): UserRegistration {
        if (this.status == UserRegistrationStatus.EXPIRED) {
            throw UserRegistrationAlreadyExpiredException()
        }

        return UserRegistration(
            this.id,
            this.login,
            this.password,
            this.email,
            this.firstName,
            this.lastName,
            status = UserRegistrationStatus.EXPIRED,
            events = mutableListOf(UserRegistrationExpired(this.id.value)),
            registerDate = this.registerDate
        )
    }

    fun createUser(): User {
        if (this.status != UserRegistrationStatus.CONFIRMED) {
            throw UserRegistrationNotConfirmedException(this.login)
        }

        return UserFactory.createUserFromUserRegistration(
            this.id.value,
            this.login,
            this.password,
            this.email,
            this.firstName,
            this.lastName
        )
    }

}

package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import cabanas.garcia.ismael.meetup.shared.domain.AggregateRoot
import cabanas.garcia.ismael.meetup.useraccess.domain.user.User
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserFactory
import java.time.Instant

class UserRegistration (
    val id: UserRegistrationId,
    val login: String,
    val password: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : AggregateRoot() {
    private var status: UserRegistrationStatus = UserRegistrationStatus.WAITING_FOR_CONFIRMATION
    private lateinit var registerDate: Instant

    fun register(usersCounter: UsersCounter): Unit =
        if (usersCounter.countUsersByLogin(this.login) == 0) {
            this.status = UserRegistrationStatus.WAITING_FOR_CONFIRMATION
            this.registerDate = Instant.now()
            registerDomainEvent(NewUserRegistered(id.value, login, email, firstName, lastName))
        }
        else throw UserRegistrationAlreadyExistException(this.login)

    fun confirm() {
        if (this.status == UserRegistrationStatus.CONFIRMED) {
            throw UserRegistrationAlreadyConfirmedException(this.login)
        } else if (this.status == UserRegistrationStatus.EXPIRED) {
            throw UserRegistrationAlreadyExpiredException()
        }

        this.status = UserRegistrationStatus.CONFIRMED
        registerDomainEvent(UserRegistrationConfirmed(this.id.value))
    }

    fun expire() {
        if (this.status == UserRegistrationStatus.EXPIRED) {
            throw UserRegistrationAlreadyExpiredException()
        }

        this.status = UserRegistrationStatus.EXPIRED
        registerDomainEvent(UserRegistrationExpired(this.id.value))
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

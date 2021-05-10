package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserCreated
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRegistrationShould {
    var usersCounter: UsersCounter = mockk()

    @BeforeEach
    fun `configure source under test`() {
        every { usersCounter.countUsersByLogin(SOME_LOGIN) } returns 0
    }

    @Test
    fun `create user registration when is the first time user wants register`() {
        val userRegistrationRegistered = UserRegistrationFactory.registerNewUser(
            SOME_USER_REGISTRATION_ID,
            SOME_LOGIN,
            SOME_PASSWORD,
            SOME_EMAIL,
            SOME_FIRST_NAME,
            SOME_LAST_NAME,
            usersCounter
        )

        userRegistrationRegistered.events() shouldContain
                NewUserRegistered(
                    userRegistrationRegistered.id.value,
                    userRegistrationRegistered.login,
                    userRegistrationRegistered.email,
                    userRegistrationRegistered.firstName,
                    userRegistrationRegistered.lastName
                )
    }

    @Test
    fun `not create an user registration when user has registered before`() {
        every { usersCounter.countUsersByLogin(SOME_LOGIN) } returns 1

        val exception = shouldThrow<UserRegistrationAlreadyExistException> {
            UserRegistrationFactory.registerNewUser(
                SOME_USER_REGISTRATION_ID,
                SOME_LOGIN,
                SOME_PASSWORD,
                SOME_EMAIL,
                SOME_FIRST_NAME,
                SOME_LAST_NAME,
                usersCounter
            )
        }

        exception.message shouldBe "$SOME_LOGIN has already registered."
    }

    @Test
    fun `confirm an user registration waiting for confirmation`() {
        val userRegistration = UserRegistrationMother.aUserRegistrationRegistered()

        userRegistration.confirm()

        userRegistration.events() shouldContain
                UserRegistrationConfirmed(
                    userRegistration.id.value

                )
    }

    @Test
    fun `fail when confirm an user registration already confirmed`() {
        val userRegistrationConfirmed = UserRegistrationMother.aUserRegistrationConfirmed()

        val exception = shouldThrow<UserRegistrationAlreadyConfirmedException> {
            userRegistrationConfirmed.confirm()
        }

        exception.message shouldBe "${userRegistrationConfirmed.login} has already confirmed the registration."
    }

    @Test
    fun `expire an user registration waiting for confirmation`() {
        val userRegistration = UserRegistrationMother.aUserRegistrationRegistered()

        userRegistration.expire()

        userRegistration.events() shouldContain
                UserRegistrationExpired(
                    userRegistration.id.value

                )
    }

    @Test
    fun `fail to confirm an expired user registration`() {
        val userRegistrationExpired = UserRegistrationMother.aUserRegistrationExpired()

        val exception = shouldThrow<UserRegistrationAlreadyExpiredException> {
            userRegistrationExpired.confirm()
        }

        exception.message shouldBe "The user registration expired."
    }

    @Test
    fun `fail to expire an user registration already expired`() {
        val userRegistrationExpired = UserRegistrationMother.aUserRegistrationExpired()

        val exception = shouldThrow<UserRegistrationAlreadyExpiredException> {
            userRegistrationExpired.expire()
        }

        exception.message shouldBe "The user registration expired."
    }

    @Test
    fun `create an user when the user registration is confirmed`() {
        val userRegistrationConfirmed = UserRegistrationMother.aUserRegistrationConfirmed()

        val user = userRegistrationConfirmed.createUser()

        user.events() shouldContain
                UserCreated(
                    user.id.value,
                    user.login,
                    user.email,
                    user.firstName,
                    user.lastName
                )
        user.id.value shouldBe userRegistrationConfirmed.id.value
        user.isActive shouldBe true
    }

    @Test
    fun `fail to create an user when the user registration is not confirmed`() {
        val userRegistrationRegistered = UserRegistrationMother.aUserRegistrationRegistered()

        val exception = shouldThrow<UserRegistrationNotConfirmedException> {
            userRegistrationRegistered.createUser()
        }

        exception.message shouldBe "${userRegistrationRegistered.login} has not confirmed the registration."
    }

    private companion object {
        private const val SOME_USER_REGISTRATION_ID = "some id"
        private const val SOME_LOGIN = "some login"
        private const val SOME_PASSWORD = "some password"
        private const val SOME_EMAIL = "some email"
        private const val SOME_FIRST_NAME = "some first name"
        private const val SOME_LAST_NAME = "some last name"
    }
}
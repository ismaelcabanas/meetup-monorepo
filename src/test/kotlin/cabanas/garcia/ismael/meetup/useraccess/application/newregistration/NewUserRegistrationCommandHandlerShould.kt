package cabanas.garcia.ismael.meetup.useraccess.application.newregistration

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test

class NewUserRegistrationCommandHandlerShould {
    private var userRegistrationRepository: UserRegistrationRepository = mockk(relaxed = true)
    private var eventBus: EventBus = mockk(relaxed = true)
    private var usersCounter: UsersCounter = mockk(relaxed = true)

    @Test
    fun `save a successful user registration`() {
        val handler = NewUserRegistrationCommandHandler(userRegistrationRepository, usersCounter, eventBus)
        val command = NewUserRegistrationCommandMother.random()

        handler.handle(command)

        shouldHaveSaved(expectedUserRegistrationFromCommand(command))
        shouldHavePublished(expectedNewUserRegisteredFromCommand(command))
    }

    @Test
    fun `don't save an user registration of an existent user`() {
        val handler = NewUserRegistrationCommandHandler(userRegistrationRepository, usersCounter, eventBus)
        val command = NewUserRegistrationCommandMother.random()
        every { usersCounter.countUsersByLogin(command.email) } returns 1

        catchThrowable {
            handler.handle(command)
        }

        shouldHaveNotSavedUserRegistration()
    }

    private fun shouldHaveNotSavedUserRegistration() {
        verify(exactly = 0) {
            userRegistrationRepository.save(any())
        }
    }

    private fun expectedUserRegistrationFromCommand(command: NewUserRegistrationCommand): UserRegistration =
        UserRegistration(
            UserRegistrationId(command.id),
            command.email,
            command.password,
            command.email,
            command.firstName,
            command.lastName)

    private fun expectedNewUserRegisteredFromCommand(command: NewUserRegistrationCommand) =
        NewUserRegistered(
            command.id,
            command.email,
            command.email,
            command.firstName,
            command.lastName
        )

    private fun shouldHavePublished(expected: NewUserRegistered) {
        verify {
            eventBus.publish(
                match {
                    it.contains(
                        expected
                    )
                }
            )
        }
    }

    private fun shouldHaveSaved(expected: UserRegistration) {
        verify {
            userRegistrationRepository.save(
                withArg {
                    assertThat(expected.id.value).isEqualTo(it.id.value)
                    assertThat(expected.firstName).isEqualTo(it.firstName)
                    assertThat(expected.lastName).isEqualTo(it.lastName)
                    assertThat(expected.email).isEqualTo(it.email)
                    assertThat(expected.password).isEqualTo(it.password)
                }
            )
        }
    }
}
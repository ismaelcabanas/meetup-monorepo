package cabanas.garcia.ismael.meetup.useraccess.application.newregistration

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.*
import io.mockk.verify
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
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

        val expectedUserRegistration = userRegistrationFromCommand(command)
        shouldHaveSaved(expectedUserRegistration)
        shouldHavePublished(NewUserRegistered(
            command.id,
            command.email,
            command.email,
            command.firstName,
            command.lastName
        ))
    }

    private fun userRegistrationFromCommand(command: NewUserRegistrationCommand): UserRegistration =
        UserRegistration(
            UserRegistrationId(command.id),
            command.email,
            command.password,
            command.email,
            command.firstName,
            command.lastName)

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
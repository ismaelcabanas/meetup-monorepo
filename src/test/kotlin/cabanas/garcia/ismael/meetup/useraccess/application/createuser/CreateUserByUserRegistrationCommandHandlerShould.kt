package cabanas.garcia.ismael.meetup.useraccess.application.createuser

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserCreated
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationMother
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class CreateUserByUserRegistrationCommandHandlerShould {
    private var userRegistrationRepository: UserRegistrationRepository = mockk(relaxed = true)
    private var userRepository: UserRepository = mockk(relaxed = true)
    private var eventBus: EventBus = mockk(relaxed = true)

    @Test
    fun `save an user`() {
        val userRegistrationConfirmed = UserRegistrationMother.aUserRegistrationConfirmed()
        val handler = CreateUserByUserRegistrationCommandHandler(userRegistrationRepository, userRepository, eventBus)
        val command = CreateUserByUserRegistrationCommand(userRegistrationId = UUID.randomUUID().toString())
        every { userRegistrationRepository.findBy(UserRegistrationId(command.userRegistrationId)) } returns
                userRegistrationConfirmed

        handler.handle(command)

        shouldHaveSavedUserWithId(userRegistrationConfirmed.id.value)
        shouldHavePublished(
            UserCreated(
                userRegistrationConfirmed.id.value,
                userRegistrationConfirmed.login,
                userRegistrationConfirmed.email,
                userRegistrationConfirmed.firstName,
                userRegistrationConfirmed.lastName
            )
        )
    }

    private fun shouldHaveSavedUserWithId(expectedUserId: String) {
        verify {
            userRepository.save(
                withArg {
                    assertThat(expectedUserId).isEqualTo(it.id.value)
                }
            )
        }
    }

    private fun shouldHavePublished(expectedUserCreated: UserCreated) {
        verify {
            eventBus.publish(
                match {
                    it.contains(expectedUserCreated)
                }
            )
        }
    }
}
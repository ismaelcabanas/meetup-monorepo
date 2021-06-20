package cabanas.garcia.ismael.meetup.useraccess.application.confirmregistration

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationConfirmed
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationMother
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConfirmUserRegistrationCommandHandlerShould {
    private var userRegistrationRepository: UserRegistrationRepository = mockk(relaxed = true)
    private var eventBus: EventBus = mockk(relaxed = true)

    @Test
    fun `save a successful user registration confirmed`() {
        val userRegistrationRegistered = UserRegistrationMother.aUserRegistrationRegistered()
        val handler = ConfirmUserRegistrationCommandHandler(userRegistrationRepository, eventBus)
        val command = ConfirmUserRegistrationCommand(userRegistrationRegistered.id.value)
        every { userRegistrationRepository.findBy(UserRegistrationId(command.userRegistrationId)) } returns
                userRegistrationRegistered

        handler.handle(command)

        shouldHaveSaved(userRegistrationRegistered.id)
        shouldHavePublished(
            UserRegistrationConfirmed(
                userRegistrationRegistered.id.value
            )
        )
    }

    private fun shouldHavePublished(expectedUserRegistrationConfirmed: UserRegistrationConfirmed) {
        verify {
            eventBus.publish(
                match {
                    it.contains(expectedUserRegistrationConfirmed)
                }
            )
        }
    }

    private fun shouldHaveSaved(expectedUserRegistrationId: UserRegistrationId) {
        verify {
            userRegistrationRepository.save(
                withArg {
                    assertThat(expectedUserRegistrationId).isEqualTo(it.id)
                }
            )
        }
    }
}
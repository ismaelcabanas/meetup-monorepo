package cabanas.garcia.ismael.meetup.useraccess.application.authentication

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.User
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserAuthenticated
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserId
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*

class AuthenticateUserCommandHandlerShould {
    private var userRepository: UserRepository = mockk(relaxed = true)
    private var eventBus: EventBus = mockk(relaxed = true)

    @Test
    fun `publish event when user is successfully authenticated`() {
        val handler = AuthenticateUserCommandHandler(userRepository, eventBus)
        val command = AuthenticateUserCommandMother.random()
        every { userRepository.findBy(command.login, command.password) } returns
                User.Builder(
                    UserId(UUID.randomUUID().toString()),
                    command.login,
                    command.password,
                    command.login,
                    MotherCreator.faker().name().firstName()
                ).build()

        handler.handle(command)

        shouldHavePublished(
            UserAuthenticated(
                command.login,
                command.password
            )
        )
    }

    private fun shouldHavePublished(expectedUserAuthenticated: UserAuthenticated) {
        verify {
            eventBus.publish(
                match {
                    it.contains(expectedUserAuthenticated)
                }
            )
        }
    }
}
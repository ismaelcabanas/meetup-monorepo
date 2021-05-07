package cabanas.garcia.ismael.meetup.useraccess.eventhandler.spring

import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserCreated
import cabanas.garcia.ismael.meetup.useraccess.domain.user.UserRepository
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationConfirmed
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationMother
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import cabanas.garcia.ismael.meetup.useraccess.infrastructure.eventhandler.spring.UserEventHandler
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class UserEventHandlerShould {
    @MockkBean(relaxed = true)
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    @MockkBean(relaxed = true)
    private lateinit var userRepository: UserRepository

    @MockkBean(relaxed = true)
    private lateinit var eventBus: EventBus

    @Autowired
    private lateinit var userEventHandler: UserEventHandler

    @Test
    fun `save user when user confirmed registration`() {
        val userRegistrationConfirmed = UserRegistrationMother.aUserRegistrationConfirmed()
        every {
            userRegistrationRepository.findBy(userRegistrationConfirmed.id)
        } returns userRegistrationConfirmed

        userEventHandler.handleUserRegistrationConfirmed(
            UserRegistrationConfirmed(userRegistrationConfirmed.id.value)
        )

        verify {
            userRepository.save(
                withArg {
                    it.userId shouldNotBe null
                    it.login shouldBe userRegistrationConfirmed.login
                    it.password shouldBe userRegistrationConfirmed.password
                    it.email shouldBe userRegistrationConfirmed.email
                    it.firstName shouldBe userRegistrationConfirmed.firstName
                    it.lastName shouldBe userRegistrationConfirmed.lastName
                }
            )
        }
        verify {
            eventBus.publish(
                withArg {
                    it.size shouldBe 1
                    with(it[0] as UserCreated) {
                        this.id shouldNotBe null
                        this.login shouldBe userRegistrationConfirmed.login
                        this.email shouldBe userRegistrationConfirmed.email
                        this.firstName shouldBe userRegistrationConfirmed.firstName
                        this.lastName shouldBe userRegistrationConfirmed.lastName
                    }
                }
            )
        }
    }
}

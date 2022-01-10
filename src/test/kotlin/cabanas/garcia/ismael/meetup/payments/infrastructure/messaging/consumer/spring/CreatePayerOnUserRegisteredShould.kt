package cabanas.garcia.ismael.meetup.payments.infrastructure.messaging.consumer.spring

import cabanas.garcia.ismael.meetup.payment.application.createpayer.CreatePayerCommand
import cabanas.garcia.ismael.meetup.payment.infrastructure.configuration.PaymentEventHandlerConfiguration
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import cabanas.garcia.ismael.meetup.shared.infrastructure.configuration.EventBusConfiguration
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.NewUserRegisteredMother
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    classes = [
        EventBusConfiguration::class,
        PaymentEventHandlerConfiguration::class,
        CreatePayerOnUserRegisteredShould.CustomTestConfiguration::class
    ]
)
class CreatePayerOnUserRegisteredShould {

    @Autowired private lateinit var commandBus: CommandBus

    @Autowired private lateinit var eventBus: EventBus

    @Test
    fun `handle user registered event successfully`() {
        val userRegistered = NewUserRegisteredMother.random()

        eventBus.publish(listOf(userRegistered))

        verify {
            commandBus.dispatch(
                CreatePayerCommand(
                    userRegistered.id,
                    userRegistered.email,
                    userRegistered.firstName,
                    userRegistered.lastName
                )
            )
        }
    }

    @TestConfiguration
    class CustomTestConfiguration {
        @Bean
        fun mockCommandBus(): CommandBus = mockk(relaxed = true)
    }
}
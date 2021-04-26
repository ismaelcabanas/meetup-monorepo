package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UsersCounter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {
    @Bean
    fun usersCounter(): UsersCounter = DummyUsersCounter()
}

class DummyUsersCounter : UsersCounter {
    override fun countUsersByLogin(login: String): Int {
        return 0
    }

}

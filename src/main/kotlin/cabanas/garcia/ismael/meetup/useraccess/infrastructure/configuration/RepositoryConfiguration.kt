package cabanas.garcia.ismael.meetup.useraccess.infrastructure.configuration

import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistration
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationId
import cabanas.garcia.ismael.meetup.useraccess.domain.userregistration.UserRegistrationRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun userRegistrationRepository(): UserRegistrationRepository = DummyUserRegistrationRepository()
}

class DummyUserRegistrationRepository : UserRegistrationRepository {
    override fun save(userRegistration: UserRegistration) {
        TODO("Not yet implemented")
    }

    override fun findBy(id: UserRegistrationId): UserRegistration {
        TODO("Not yet implemented")
    }
}

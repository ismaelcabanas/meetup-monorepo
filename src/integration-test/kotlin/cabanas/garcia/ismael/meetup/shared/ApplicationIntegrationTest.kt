package cabanas.garcia.ismael.meetup.shared

import cabanas.garcia.ismael.meetup.payment.infrastructure.repository.jpa.JpaPayerRepositoryShould
import cabanas.garcia.ismael.meetup.shared.helper.DockerComposeHelper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class ApplicationIntegrationTest {

    companion object {

        @Container
        private val dockerComposeContainer = DockerComposeHelper.create()

        @BeforeAll
        @JvmStatic
        fun setSystemProperties() {
            DockerComposeHelper.setSystemProperties(dockerComposeContainer)
        }
    }

    @Nested
    inner class PayerRepository : JpaPayerRepositoryShould()
}
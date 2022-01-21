package cabanas.garcia.ismael.meetup.shared.helper

import java.io.File
import java.lang.System.setProperty
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.wait.strategy.WaitAllStrategy

class DockerComposeHelper {

    companion object {

        private const val POSTGRES = "postgres"
        private const val POSTGRES_PORT = 5432

        fun create(): DockerComposeContainer<*> {
            return DockerComposeContainer<Nothing>(File("docker-compose.yml"))
                .apply { withLocalCompose(true) }
                .apply {
                    withExposedService(
                        POSTGRES,
                        POSTGRES_PORT,
                        WaitAllStrategy(WaitAllStrategy.Mode.WITH_INDIVIDUAL_TIMEOUTS_ONLY)
                            .apply { withStrategy(Wait.forListeningPort()) }
                            .apply { withStrategy(Wait.forLogMessage(".*database system is ready to accept connections.*", 1)) }
                    )
                }
        }

        fun setSystemProperties(container: DockerComposeContainer<*>) {
            setProperty("database.host", container.getServiceHost(POSTGRES, POSTGRES_PORT))
            setProperty("database.port", container.getServicePort(POSTGRES, POSTGRES_PORT).toString())
        }
    }
}
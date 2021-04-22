package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cors")
data class CORSProperties(val allowedOrigins: List<String>)

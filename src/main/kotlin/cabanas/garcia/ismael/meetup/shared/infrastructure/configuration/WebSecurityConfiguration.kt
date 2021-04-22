package cabanas.garcia.ismael.meetup.shared.infrastructure.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = [CORSProperties::class])
class WebSecurityConfiguration(
    private val corsProperties: CORSProperties
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .cors().configurationSource(configurationSource())
            .and().csrf().disable()
    }

    private fun configurationSource() = UrlBasedCorsConfigurationSource().apply {
        registerCorsConfiguration("/**", configureCORS())
    }

    private fun configureCORS() = CorsConfiguration().apply {
        allowedHeaders = listOf("*")
        allowedMethods = listOf(
            HttpMethod.GET.name,
            HttpMethod.POST.name,
            HttpMethod.DELETE.name,
            HttpMethod.OPTIONS.name,
            HttpMethod.PUT.name,
            HttpMethod.PATCH.name,
            HttpMethod.HEAD.name
        )
        allowedOrigins = corsProperties.allowedOrigins
    }
}
package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.application.authentication.AuthenticateUserCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostAuthenticateUserController(
    private val commandBus: CommandBus
) {
    @RequestMapping("/v1/users")
    @PostMapping
    fun execute(@RequestBody requestBody: AuthenticateRequestBody): ResponseEntity<Void> =
        commandBus.dispatch(toCommand(requestBody))
            .let {
                ResponseEntity.ok().build()
            }

    private fun toCommand(requestBody: AuthenticateRequestBody) =
        AuthenticateUserCommand(requestBody.username, requestBody.password)

}

data class AuthenticateRequestBody(val username: String, val password: String)
package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import cabanas.garcia.ismael.meetup.useraccess.application.rergistration.confirm.ConfirmUserRegistrationCommand
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PatchConfirmUserRegistrationController(
    private val commandBus: CommandBus
) {
    @RequestMapping("/v1/user-registrations/{userRegistrationId}/confirm")
    @PatchMapping
    fun execute(@PathVariable("userRegistrationId") userRegistrationId: String) : ResponseEntity<Void> =
        commandBus
            .dispatch(toCommand(userRegistrationId))
            .let {
                ResponseEntity(OK)
            }

    private fun toCommand(userRegistrationId: String) =
        ConfirmUserRegistrationCommand(userRegistrationId)
}
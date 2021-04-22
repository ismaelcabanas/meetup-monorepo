package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.util.UUID

object CreateUserRegistrationRequestMother {
    fun random() =
        CreateUserRegistrationRequest(
            UUID.randomUUID().toString(),
            MotherCreator.faker().name().firstName(),
            MotherCreator.faker().name().lastName(),
            MotherCreator.faker().internet().emailAddress(),
            MotherCreator.faker().internet().password()
        )
}
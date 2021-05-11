package cabanas.garcia.ismael.meetup.useraccess.application.newregistration

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.util.*

object NewUserRegistrationCommandMother {
    fun random(): NewUserRegistrationCommand =
        NewUserRegistrationCommand(
            UUID.randomUUID().toString(),
            MotherCreator.faker().name().firstName(),
            MotherCreator.faker().name().lastName(),
            MotherCreator.faker().internet().emailAddress(),
            MotherCreator.faker().internet().password()
        )
}

package cabanas.garcia.ismael.meetup.useraccess.application.authentication

import cabanas.garcia.ismael.meetup.shared.MotherCreator

object AuthenticateUserCommandMother {
    fun random() =
        AuthenticateUserCommand(
            MotherCreator.faker().internet().emailAddress(),
            MotherCreator.faker().internet().password()
        )
}
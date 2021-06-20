package cabanas.garcia.ismael.meetup.useraccess.api.v1

import cabanas.garcia.ismael.meetup.shared.MotherCreator

object AuthenticateUserRequestMother {
    fun random() =
        AuthenticateRequestBody(
            MotherCreator.faker().internet().emailAddress(),
            MotherCreator.faker().internet().password()
        )

}
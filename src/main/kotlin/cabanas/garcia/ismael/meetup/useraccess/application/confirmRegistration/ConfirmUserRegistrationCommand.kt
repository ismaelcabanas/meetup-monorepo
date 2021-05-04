package cabanas.garcia.ismael.meetup.useraccess.application.confirmRegistration

import cabanas.garcia.ismael.meetup.shared.application.Command

data class ConfirmUserRegistrationCommand(val userRegistrationId: String): Command

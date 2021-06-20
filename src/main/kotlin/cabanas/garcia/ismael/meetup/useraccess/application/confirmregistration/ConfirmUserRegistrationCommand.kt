package cabanas.garcia.ismael.meetup.useraccess.application.confirmregistration

import cabanas.garcia.ismael.meetup.shared.application.Command

data class ConfirmUserRegistrationCommand(val userRegistrationId: String): Command

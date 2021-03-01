package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import java.lang.Exception

class UserRegistrationNotConfirmedException(val login: String): Exception("$login has not confirmed the registration.")
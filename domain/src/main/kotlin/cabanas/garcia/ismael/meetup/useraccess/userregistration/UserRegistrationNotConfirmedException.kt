package cabanas.garcia.ismael.meetup.useraccess.userregistration

import java.lang.Exception

class UserRegistrationNotConfirmedException(val login: String): Exception("$login has not confirmed the registration.")
package cabanas.garcia.ismael.meetup.useraccess.domain.user

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class UserAuthenticated(val login: String, val password: String) : DomainEvent

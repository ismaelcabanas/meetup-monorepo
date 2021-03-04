package cabanas.garcia.ismael.meetup.useraccess.domain.userregistration

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class UserRegistrationExpired(val userRegistrationId: String) : DomainEvent
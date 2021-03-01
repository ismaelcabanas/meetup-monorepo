package cabanas.garcia.ismael.meetup.domain.useraccess.user

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

data class UserCreated(
    val id: String,
    val login: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : DomainEvent
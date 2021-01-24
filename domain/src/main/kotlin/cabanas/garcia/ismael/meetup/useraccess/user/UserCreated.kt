package cabanas.garcia.ismael.meetup.useraccess.user

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class UserCreated(
    val id: String,
    val login: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : DomainEvent
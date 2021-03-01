package cabanas.garcia.ismael.meetup.domain.useraccess.userregistration

import cabanas.garcia.ismael.meetup.domain.shared.DomainEvent

data class NewUserRegistered (
    val id: String,
    val login: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : DomainEvent
package cabanas.garcia.ismael.meetup.useraccess.domain.user

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

data class UserCreated(
    val id: String?,
    val login: String?,
    val email: String?,
    val firstName:String?,
    val lastName: String?
) : DomainEvent
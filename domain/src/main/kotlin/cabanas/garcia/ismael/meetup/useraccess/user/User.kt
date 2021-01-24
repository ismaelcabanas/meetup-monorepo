package cabanas.garcia.ismael.meetup.useraccess.user

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class User(
    val userId: String,
    val login: String,
    val password: String,
    val email: String,
    val firstName:String,
    val lastName: String,
    val events: List<DomainEvent> = mutableListOf(UserCreated(userId, login, email, firstName, lastName))
) {
    private val id: UserId = UserId(userId)
    val isActive: Boolean = true

    fun id(): String = id.value

    fun events(): List<DomainEvent> = events.toList()
}
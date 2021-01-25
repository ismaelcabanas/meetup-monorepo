package cabanas.garcia.ismael.meetup.useraccess.user

import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent

data class User(
    val userId: String,
    val login: String,
    val password: String,
    val email: String,
    val firstName:String,
    val lastName: String
) {
    private val id: UserId = UserId(userId)
    private val roles = mutableListOf<Role>()
    private var events: List<DomainEvent> = mutableListOf()
    val isActive: Boolean = true

    private constructor(
        userId: String,
        login: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String,
        events: List<DomainEvent>
    ) : this(
        userId,
        login,
        password,
        email,
        firstName,
        lastName
    ) {
        this.events = events
    }

    fun id(): String = id.value

    fun events(): List<DomainEvent> = events.toList()

    fun addRole(role: Role): User {
        val user = User(
            this.id.value,
            this.login,
            this.password,
            this.email,
            this.firstName,
            this.lastName,
            this.events
        )
        user.roles.add(role)

        return user
    }

    fun roles(): List<Role> = roles.toList()
}
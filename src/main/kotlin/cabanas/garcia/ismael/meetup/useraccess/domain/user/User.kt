package cabanas.garcia.ismael.meetup.useraccess.domain.user

import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent

class User private constructor(
    builder: Builder
) {
    val id: UserId = builder.id
    val login: String = builder.login
    val password: String = builder.password
    val email: String = builder.email
    val firstName:String = builder.firstName
    val lastName: String? = builder.lastName
    private val roles = mutableListOf<Role>()
    private var events: MutableList<DomainEvent> = mutableListOf()
    val isActive: Boolean = true

    companion object {
        @JvmStatic
        fun create(
            id: UserId,
            login: String,
            password: String,
            email: String,
            firstName: String,
            lastName: String
        ): User =
            Builder(id, login, password, email, firstName, lastName)
                .build()
                .apply {
                    events.add(UserCreated(id.value, login, email, firstName, lastName))
                }
    }

    fun events(): List<DomainEvent> = events.toList()

    fun addRole(role: Role): User =
        this.apply {
            roles.add(role)
        }

    fun roles(): List<Role> = roles.toList()

    data class Builder (
        val id: UserId,
        val login: String,
        val password: String,
        val email: String,
        val firstName: String,
        var lastName: String? = null
    ) {

        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun build() = User(this)
    }
}
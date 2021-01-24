package cabanas.garcia.ismael.meetup.useraccess.userregistration

data class NewUserRegistered (
    val id: String,
    val login: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : DomainEvent
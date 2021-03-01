package cabanas.garcia.ismael.meetup.domain.administration

data class User(val id: UserId, val rol: String) {
    fun isAdmin(): Boolean {
        return rol == "ADMIN"
    }
}
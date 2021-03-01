package cabanas.garcia.ismael.meetup.administration.domain

data class User(val id: UserId, val rol: String) {
    fun isAdmin(): Boolean {
        return rol == "ADMIN"
    }
}
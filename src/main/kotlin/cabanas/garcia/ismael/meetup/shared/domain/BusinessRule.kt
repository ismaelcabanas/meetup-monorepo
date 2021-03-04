package cabanas.garcia.ismael.meetup.shared.domain

interface BusinessRule {
    fun isBroken(): Boolean
    fun message(): String
}
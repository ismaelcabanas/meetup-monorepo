package cabanas.garcia.ismael.meetup.shared.domain

open class DomainException(private val customMessage: String) : Exception(customMessage) {
}
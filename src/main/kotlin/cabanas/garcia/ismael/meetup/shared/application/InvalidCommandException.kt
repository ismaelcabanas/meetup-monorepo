package cabanas.garcia.ismael.meetup.shared.application

data class InvalidCommandException(val reason: String) : Exception(reason)

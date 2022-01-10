package cabanas.garcia.ismael.meetup.payment.application.createpayer

import cabanas.garcia.ismael.meetup.shared.application.Command

data class CreatePayerCommand(
    val userId: String,
    val email: String,
    val firstName:String,
    val lastName: String
) : Command

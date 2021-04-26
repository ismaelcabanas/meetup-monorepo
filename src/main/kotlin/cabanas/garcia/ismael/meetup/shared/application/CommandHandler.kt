package cabanas.garcia.ismael.meetup.shared.application

import cabanas.garcia.ismael.meetup.useraccess.application.rergistration.CreateUserRegistrationCommand

interface CommandHandler {
    fun handle(command: CreateUserRegistrationCommand)
}
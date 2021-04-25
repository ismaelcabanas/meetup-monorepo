package cabanas.garcia.ismael.meetup.shared.application

import cabanas.garcia.ismael.meetup.useraccess.application.rergistration.CreateUserRegistrationCommand

class DummyCommandBus : CommandBus {
    override fun dispatch(command: CreateUserRegistrationCommand) {

    }
}
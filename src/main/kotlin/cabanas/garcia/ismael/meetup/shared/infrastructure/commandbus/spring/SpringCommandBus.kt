package cabanas.garcia.ismael.meetup.shared.infrastructure.commandbus.spring

import cabanas.garcia.ismael.meetup.shared.application.Command
import cabanas.garcia.ismael.meetup.shared.application.CommandBus

class SpringCommandBus(
    private val registry: Registry
) : CommandBus {
    override fun dispatch(command: Command) {
        registry.get(command::class.java).handle(command)
    }
}

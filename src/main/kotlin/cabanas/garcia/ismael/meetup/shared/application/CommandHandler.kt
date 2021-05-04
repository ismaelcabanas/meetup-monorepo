package cabanas.garcia.ismael.meetup.shared.application

interface CommandHandler<C : Command> {
    fun handle(command: C)
}
package cabanas.garcia.ismael.meetup.shared.infrastructure.commandbus.spring

import cabanas.garcia.ismael.meetup.shared.application.Command
import cabanas.garcia.ismael.meetup.shared.application.CommandHandler
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import kotlin.reflect.KClass

class Registry(
    applicationContext: ApplicationContext
) {
    private val commandHandlers: MutableMap<Class<Command>, in CommandHandler<out Command>> = mutableMapOf()

    init {
        applicationContext.getBeanNamesForType(CommandHandler::class.java).forEach {
            register(applicationContext, it)
        }
    }

    private fun register(applicationContext: ApplicationContext, beanName: String) {
        val handlerClass: Class<CommandHandler<out Command>> =
            applicationContext.getType(beanName) as Class<CommandHandler<out Command>>
        val generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler::class.java)
        val commandType = generics!![0] as Class<Command>
        commandHandlers[commandType] = applicationContext.getBean(beanName) as CommandHandler<out Command>
    }

    fun get(commandClass: Class<out Command>): CommandHandler<Command> =
        commandHandlers[commandClass] as CommandHandler<Command>
}
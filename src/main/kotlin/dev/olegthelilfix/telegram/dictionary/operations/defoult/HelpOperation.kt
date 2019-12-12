package dev.olegthelilfix.telegram.dictionary.operations.defoult

import dev.olegthelilfix.telegram.dictionary.operations.Operation

open class HelpOperation(telegramOperation: List<Operation>) : Operation {
    private val helpMessage = "Hi bro.\n" + telegramOperation.joinToString(separator = "\n") {
        "*${it.getName()}* - ${it.getDescription()}"
    }

    override fun execute(args: List<String>): List<String> = listOf(helpMessage)
    override fun getName(): String = "/help"
    override fun getDescription(): String = "HelpOperation";
}
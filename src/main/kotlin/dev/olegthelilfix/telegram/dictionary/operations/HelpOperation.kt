package dev.olegthelilfix.telegram.dictionary.operations

open class HelpOperation (telegramOperation : List<Operation>) : Operation {
    private val helpMessage = "Hi bro.\n" + telegramOperation.joinToString(separator = "") {
        "*${it.getName()}* - ${it.getDescription()}\n"
    }

    override fun execute(args: List<String>): List<String> = listOf(helpMessage)
    override fun getName(): String = "/help"
    override fun getDescription(): String = "HelpOperation";
}
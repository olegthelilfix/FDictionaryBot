package dev.olegthelilfix.telegram.dictionary.operations

class StartOperation (telegramOperation : List<Operation>) : HelpOperation(telegramOperation) {
    override fun getName(): String = "/start"
    override fun getDescription(): String = "StartOperation";
}
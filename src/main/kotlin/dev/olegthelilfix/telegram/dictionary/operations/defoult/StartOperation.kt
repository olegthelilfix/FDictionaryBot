package dev.olegthelilfix.telegram.dictionary.operations.defoult

import dev.olegthelilfix.telegram.dictionary.operations.Operation

class StartOperation (telegramOperation : List<Operation>) : HelpOperation(telegramOperation) {
    override fun getName(): String = "/start"
    override fun getDescription(): String = "StartOperation";
}
package dev.olegthelilfix.telegram.dictionary.services.operations

import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments

interface Operation {
    fun execute(args: TelegramOperationArguments) : List<String>
    fun getName() : String
    fun getDescription() : String
    fun error() : String = "птчк вс очн плх."
}
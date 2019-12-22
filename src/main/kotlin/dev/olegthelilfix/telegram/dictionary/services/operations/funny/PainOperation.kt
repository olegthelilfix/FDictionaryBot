package dev.olegthelilfix.telegram.dictionary.services.operations.funny

import dev.olegthelilfix.telegram.dictionary.services.operations.Operation
import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments
import org.springframework.stereotype.Component

@Component
class PainOperation: Operation {
    override fun execute(args: TelegramOperationArguments): List<String> = listOf("Эй ты, да ты, да пошел бы ты нахуй, бич!")
    override fun getName(): String = "/pain"
    override fun getDescription(): String = "Сделай мне больно!"
}
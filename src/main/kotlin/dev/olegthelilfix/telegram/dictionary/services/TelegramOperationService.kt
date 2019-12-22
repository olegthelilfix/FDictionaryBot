package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.services.operations.Operation
import dev.olegthelilfix.telegram.dictionary.services.operations.defoult.ErrorOperation
import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments
import org.telegram.telegrambots.api.objects.Update
import java.util.stream.Collectors

class TelegramOperationService(telegramOperation : List<Operation>) {
    private val operations: Map<String, Operation> = telegramOperation.stream().collect(Collectors.toMap({it.getName()}, {it}))

    private val errorOperation = ErrorOperation()

    fun executeOperation(update: Update): List<String> {
        val args = splitCommand(update.message.text)

        return operations.getOrDefault(args[0], errorOperation).execute(TelegramOperationArguments(args, update))
    }

    private fun splitCommand(message: String) = message.split(" ")
}
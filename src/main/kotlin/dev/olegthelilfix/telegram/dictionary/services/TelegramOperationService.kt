package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.operations.Operation
import dev.olegthelilfix.telegram.dictionary.operations.defoult.ErrorOperation
import java.util.stream.Collectors

class TelegramOperationService(telegramOperation : List<Operation>) {
    private val operations: Map<String, Operation> = telegramOperation.stream().collect(Collectors.toMap({it.getName()}, {it}))

    private val errorOperation = ErrorOperation()

    fun executeOperation(args: List<String>): List<String> {
        return operations.getOrDefault(args[0], errorOperation).execute(args)
    }
}
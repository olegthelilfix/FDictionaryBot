package dev.olegthelilfix.telegram.dictionary.managers

import dev.olegthelilfix.telegram.dictionary.operations.Operation
import java.util.stream.Collectors

class TelegramOperationService(telegramOperation : List<Operation>) {
    private val operations: Map<String, Operation> = telegramOperation.stream().collect(Collectors.toMap({it.getName()}, {it}))

    fun executeOperation(args: List<String>): List<String> {
        return operations.getOrDefault(args[0], object : Operation{
            override fun execute(args: List<String>)= listOf("моя твоя не понимай. писать по руски тогда я понимай.")
            override fun getName(): String = "/Anon"
            override fun getDescription(): String = "/Anon"

        }).execute(args)
    }
}
package dev.olegthelilfix.telegram.dictionary.operations.defoult

import dev.olegthelilfix.telegram.dictionary.operations.Operation

class ErrorOperation: Operation {
    override fun execute(args: List<String>)= listOf("моя твоя не понимай. писать по руски тогда я понимай.")
    override fun getName(): String = "/Anon"
    override fun getDescription(): String = "/Anon"
}
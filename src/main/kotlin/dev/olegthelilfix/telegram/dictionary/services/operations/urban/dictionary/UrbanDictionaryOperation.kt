package dev.olegthelilfix.telegram.dictionary.services.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.services.operations.Operation
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWordDescription

abstract class UrbanDictionaryOperation(protected val urbanDictionaryClient: UrbanDictionaryClient): Operation {
    protected fun formWordDescriptionText(message: String, info: UrbanDictionaryWordDescription): String
            = "*$message* _from UrbanDictionary_\n" +
            "*definition:*\n`${clearMessage(info.definition)}`\n" +
            "*example:*```${clearMessage(info.example)}```\n" +
            "[link](${info.permalink}) "

    protected fun urbanDictionaryBestResult(word: String): UrbanDictionaryWordDescription
            = urbanDictionaryClient.findWorld(word).list[0]

    private fun clearMessage(message: String)
            = message.replace("[", "")
            .replace("]", "")
}
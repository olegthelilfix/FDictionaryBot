package dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.services.TopWordService

class TopWordsOperation(private val topWordService: TopWordService,
                        urbanDictionaryClient: UrbanDictionaryClient) : UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: List<String>): List<String> {
        return if (args.size > 1) {
            val number: Int = args[1].toInt()

            listOf(formWordDescriptionText(topWordService.topWords[number], urbanDictionaryBestResult(topWordService.topWords[number])))
        } else {
            listOf(formBestWordText(topWordService.topWords))
        }
    }

    override fun getName(): String = "/topList"

    override fun getDescription(): String = "Список топ слов. С числом - значение слова из топ списка под данным номером."

    private fun formBestWordText(words: List<String>): String = words.joinToString(prefix = "*", postfix = "*", separator = "\n")
}
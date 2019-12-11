package dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.managers.TopWordManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopWordsOperation @Autowired constructor(
        private var topWordManager: TopWordManager,
        urbanDictionaryClient: UrbanDictionaryClient): UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: List<String>): List<String> {
        return if (args.size > 1) {
            val number: Int = args[1].toInt()

            listOf(formWordDescriptionText(topWordManager.topWords[number], urbanDictionaryBestResult(topWordManager.topWords[number])))
        }
        else {
            listOf(formBestWordText(topWordManager.topWords))
        }
    }

    override fun getName(): String = "/topList"

    override fun getDescription(): String = "Список топ слов. С числом - значение слова из топ списка под данным номером."

    private fun formBestWordText(words: List<String>): String
            = words.joinToString(prefix = "*", postfix = "*", separator = "\n")
}
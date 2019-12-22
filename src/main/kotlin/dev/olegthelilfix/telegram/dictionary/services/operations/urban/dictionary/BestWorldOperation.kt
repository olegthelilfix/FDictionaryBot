package dev.olegthelilfix.telegram.dictionary.services.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import org.springframework.stereotype.Component

@Component
class BestWorldOperation (urbanDictionaryClient: UrbanDictionaryClient): UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: List<String>): List<String> {
        if(args.size > 1) {
            val word = args[1]

            return listOf(formWordDescriptionText(word, urbanDictionaryBestResult(word)))
        }

        return listOf(error())
    }

    override fun getName(): String = "/best"

    override fun getDescription(): String = "Первое значения по указаному слову."
}
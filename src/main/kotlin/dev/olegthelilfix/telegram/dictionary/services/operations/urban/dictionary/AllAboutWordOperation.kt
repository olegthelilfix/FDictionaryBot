package dev.olegthelilfix.telegram.dictionary.services.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import org.springframework.stereotype.Component

@Component
class AllAboutWordOperation (urbanDictionaryClient: UrbanDictionaryClient): UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: List<String>): List<String> {
        if(args.size > 1) {
            val word = args[1]

            return urbanDictionaryClient.findWorld(word).list.map { formWordDescriptionText(word, it) }
        }

        return listOf(error())
    }

    override fun getName(): String = "/all"
    override fun getDescription(): String = "Все доступные значения по указаному слову."
}
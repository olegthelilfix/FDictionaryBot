package dev.olegthelilfix.telegram.dictionary.services.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments
import org.springframework.stereotype.Component

@Component
class BestWorldOperation (urbanDictionaryClient: UrbanDictionaryClient): UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: TelegramOperationArguments): List<String> {
        if(args.list.size > 1) {
            val word = args.list[1]

            return listOf(formWordDescriptionText(word, urbanDictionaryBestResult(word)))
        }

        return listOf(error())
    }

    override fun getName(): String = "/best"

    override fun getDescription(): String = "Первое значения по указаному слову."
}
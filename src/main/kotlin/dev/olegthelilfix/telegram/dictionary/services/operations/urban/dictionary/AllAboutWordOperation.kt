package dev.olegthelilfix.telegram.dictionary.services.operations.urban.dictionary

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments
import org.springframework.stereotype.Component

@Component
class AllAboutWordOperation (urbanDictionaryClient: UrbanDictionaryClient): UrbanDictionaryOperation(urbanDictionaryClient) {
    override fun execute(args: TelegramOperationArguments): List<String> {
        if(args.list.size > 1) {
            val word = args.list[1]

            return urbanDictionaryClient.findWorld(word).list.map { formWordDescriptionText(word, it) }
        }

        return listOf(error())
    }

    override fun getName(): String = "/all"
    override fun getDescription(): String = "Все доступные значения по указаному слову."
}
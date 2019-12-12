package dev.olegthelilfix.telegram.dictionary.conf

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.conf.settings.TelegramBotSettings
import dev.olegthelilfix.telegram.dictionary.conf.settings.UrbanDictionarySettings
import dev.olegthelilfix.telegram.dictionary.operations.Operation
import dev.olegthelilfix.telegram.dictionary.operations.defoult.HelpOperation
import dev.olegthelilfix.telegram.dictionary.operations.defoult.StartOperation
import dev.olegthelilfix.telegram.dictionary.operations.funny.PainOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.AllAboutWordOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.BestWorldOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.TopWordsOperation
import dev.olegthelilfix.telegram.dictionary.services.TelegramOperationService
import dev.olegthelilfix.telegram.dictionary.services.TopWordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
class AppConfiguration {
    @Autowired
    private lateinit var telegramOperation : MutableList<Operation>

    @Bean
    fun createTelegramOperation() : TelegramOperationService {
        telegramOperation.add(HelpOperation(telegramOperation))
        // Добавляем операцию start после операции help, для того что бы в ней была инфа о команде помощи
        telegramOperation.add(StartOperation(telegramOperation))

        return TelegramOperationService(telegramOperation)
    }
}

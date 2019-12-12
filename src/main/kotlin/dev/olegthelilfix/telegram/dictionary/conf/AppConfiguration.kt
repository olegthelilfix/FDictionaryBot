package dev.olegthelilfix.telegram.dictionary.conf

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.services.TelegramOperationService
import dev.olegthelilfix.telegram.dictionary.services.TopWordService
import dev.olegthelilfix.telegram.dictionary.operations.defoult.HelpOperation
import dev.olegthelilfix.telegram.dictionary.operations.Operation
import dev.olegthelilfix.telegram.dictionary.operations.funny.PainOperation
import dev.olegthelilfix.telegram.dictionary.operations.defoult.StartOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.AllAboutWordOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.BestWorldOperation
import dev.olegthelilfix.telegram.dictionary.operations.urban.dictionary.TopWordsOperation
import dev.olegthelilfix.telegram.dictionary.conf.settings.TelegramBotSettings
import dev.olegthelilfix.telegram.dictionary.conf.settings.UrbanDictionarySettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:settings.properties")
class AppConfiguration {
    @Value("\${telegram.botUserName}")
    private lateinit var botUserName: String

    @Value("\${telegram.botToken}")
    private lateinit var token: String

    @Value("\${urbanDictionary.url}")
    private lateinit var url: String

    @Value("\${urbanDictionary.host}")
    private lateinit var host: String

    @Value("\${urbanDictionary.key}")
    private lateinit var key: String

    @Value("\${urbanDictionary.mainPageUrl}")
    private lateinit var mainPageUrl: String

    @Autowired
    private lateinit var topWordService: TopWordService

    @Autowired
    private lateinit var urbanDictionarySettings: UrbanDictionarySettings

    private var telegramOperation : MutableList<Operation> = mutableListOf()

    @Bean
    fun createTelegramOperation() : TelegramOperationService {
        telegramOperation.add(AllAboutWordOperation(createUrbanDictionaryClient()))
        telegramOperation.add(BestWorldOperation(createUrbanDictionaryClient()))
        telegramOperation.add(PainOperation())
        telegramOperation.add(TopWordsOperation(topWordService, createUrbanDictionaryClient()))

        telegramOperation.add(HelpOperation(telegramOperation))
        // Добавляем операцию start после операции help, для того что бы в ней была инфа о команде помощи
        telegramOperation.add(StartOperation(telegramOperation))

        return TelegramOperationService(telegramOperation)
    }

    @Bean
    fun createUrbanDictionaryClient(): UrbanDictionaryClient {
        return UrbanDictionaryClient(urbanDictionarySettings)
    }

    @Bean
    fun createTelegramSettings() = TelegramBotSettings (botUserName, token)

    @Bean
    fun createUrbanDictionarySettings() = UrbanDictionarySettings(url, mainPageUrl, key, host)
}

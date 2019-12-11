package dev.olegthelilfix.telegram.dictionary.conf

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.managers.TelegramOperationService
import dev.olegthelilfix.telegram.dictionary.operations.HelpOperation
import dev.olegthelilfix.telegram.dictionary.operations.Operation
import dev.olegthelilfix.telegram.dictionary.operations.StartOperation
import dev.olegthelilfix.telegram.settings.TelegramBotSettings
import dev.olegthelilfix.telegram.settings.UrbanDictionarySettings
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

    @Autowired
    private lateinit var urbanDictionarySettings: UrbanDictionarySettings

    @Autowired
    private lateinit var telegramOperation : MutableList<Operation>

    @Bean
    fun createTelegramOperation() : TelegramOperationService {
        val helpOperation = HelpOperation(telegramOperation)
        val startOperation = StartOperation(telegramOperation)

        telegramOperation.add(helpOperation)
        telegramOperation.add(startOperation)

        return TelegramOperationService(telegramOperation)
    }

    @Bean
    fun createUrbanDictionaryClient(): UrbanDictionaryClient {
        return UrbanDictionaryClient(urbanDictionarySettings)
    }

    @Bean
    fun createTelegramSettings() = TelegramBotSettings (botUserName, token)

    @Bean
    fun createUrbanDictionarySettings() = UrbanDictionarySettings(url, key, host)
}

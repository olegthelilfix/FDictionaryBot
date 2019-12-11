package dev.olegthelilfix.telegram.dictionary.conf

import dev.olegthelilfix.telegram.settings.TelegramBotSettings
import dev.olegthelilfix.telegram.settings.UrbanDictionarySettings
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

    @Bean
    fun createTelegramSettings() = TelegramBotSettings (botUserName, token)

    @Bean
    fun createUrbanDictionarySettings() = UrbanDictionarySettings(url, key, host)
}

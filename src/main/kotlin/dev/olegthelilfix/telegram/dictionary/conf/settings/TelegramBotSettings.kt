package dev.olegthelilfix.telegram.dictionary.conf.settings

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:settings.properties")
class TelegramBotSettings {
    @Value("\${telegram.botUserName}")
    lateinit var botUserName: String

    @Value("\${telegram.botToken}")
    lateinit var token: String
}
package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.conf.settings.TelegramBotSettings
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

@Service
final class TelegramBotService(private val telegramBotSettings: TelegramBotSettings,
                               private val telegramOperationService: TelegramOperationService) : TelegramLongPollingBot() {

    private val logger = LoggerFactory.getLogger(TelegramBotService::class.java)

    private val botsApi = TelegramBotsApi()

    companion object {
        init {
            ApiContextInitializer.init()
        }
    }

    init {
        botsApi.registerBot(this)
    }

    override fun onUpdateReceived(update: Update) {
        try {
            telegramOperationService.executeOperation(update).forEach { sendMessage(update, it) }
        } catch (e: Exception) {
            sendMessage(update, "птчк вс очн плх.\n$e")
        }
    }

    override fun getBotUsername() = telegramBotSettings.botUserName

    override fun getBotToken() = telegramBotSettings.token

    private fun sendMessage(update: Update, text: String) = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}


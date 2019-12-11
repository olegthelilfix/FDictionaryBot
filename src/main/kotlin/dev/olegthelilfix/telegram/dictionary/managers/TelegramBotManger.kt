package dev.olegthelilfix.telegram.dictionary.managers

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.operations.Operation
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWordDescription
import dev.olegthelilfix.telegram.settings.TelegramBotSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.ApiContext
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.lang.Exception

@Service
final class TelegramBotManger @Autowired constructor(
        private var telegramBotSettings: TelegramBotSettings,
        private val telegramOperationService: TelegramOperationService) : TelegramLongPollingBot(ApiContext.getInstance(DefaultBotOptions::class.java)) {
    private val botsApi = TelegramBotsApi()

    companion object {
        init {
            ApiContextInitializer.init()
        }
    }

    init {
        botsApi.registerBot(this);
    }

    override fun onUpdateReceived(update: Update) {
        try {
            val message = update.message.text

            val args: List<String> = splitCommand(message)

            telegramOperationService.executeOperation(args).forEach {sendMessage(update,it)}
        }
        catch (e: Exception) {
            sendMessage(update, "птчк вс очн плх.\n$e")
        }
    }

    override fun getBotUsername() = telegramBotSettings.botUserName

    override fun getBotToken() = telegramBotSettings.token

    private fun splitCommand(message: String) = message.split(" ")

    private fun sendMessage(update: Update, text: String)
        = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}

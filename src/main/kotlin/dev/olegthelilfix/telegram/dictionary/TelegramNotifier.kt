package dev.olegthelilfix.telegram.dictionary

import org.springframework.stereotype.Service
import org.telegram.telegrambots.ApiContext
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot

fun formBotOptions(): DefaultBotOptions
        = ApiContext.getInstance(DefaultBotOptions::class.java) as DefaultBotOptions

@Service
final class TelegramNotifier : TelegramLongPollingBot(formBotOptions())
{
    private val botsApi = TelegramBotsApi()


    private val botUserName: String = "FDictionaryBot"
    private val token: String = "731797556:AAHgblaizxG4ShR6d1k5cF-Qwb9Lf-eVGUs"

    private val urbanDictionaryClient: UrbanDictionaryClient = UrbanDictionaryClient()

    companion object {
        init {
            ApiContextInitializer.init()
        }
    }

    init {
        botsApi.registerBot(this);
    }

    override fun onUpdateReceived(update: Update) {
        val result = urbanDictionaryClient.findWorld(update.message.text)

        val html = "*${update.message.text}*\n" +
                "*definition:*\n`${clearMessage(result.list[0].definition)}`\n" +
                "*example:*```${clearMessage(result.list[0].example)}```"

        sendMessage(update, html)
    }

    override fun getBotUsername() = botUserName

    override fun getBotToken() = token

    private fun clearMessage(message: String)
            = message.replace("[", "")
                     .replace("]", "")
                     .replace("\n", "")

    private fun sendMessage(update: Update, text: String)
        = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}

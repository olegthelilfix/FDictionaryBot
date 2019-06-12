package dev.olegthelilfix.telegram.dictionary.managers

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWordDescription
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
final class TelegramBotManger : TelegramLongPollingBot(ApiContext.getInstance(DefaultBotOptions::class.java))
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
        try {
            sendMessage(update, formMessageText(update, urbanDictionaryBestResult(update.message.text)))
        }
        catch (e: Exception) {
            sendMessage(update, "птчк вс очн плх.\n$e")
        }
    }

    override fun getBotUsername() = botUserName

    override fun getBotToken() = token

    private fun urbanDictionaryBestResult(word: String): UrbanDictionaryWordDescription
            = urbanDictionaryClient.findWorld(word).list[0]

    private fun formMessageText(update: Update, info: UrbanDictionaryWordDescription): String
            = "*[${update.message.text}](${info.permalink})* [from UrbanDictionary]\n" +
              "*definition:*\n`${clearMessage(info.definition)}`\n" +
              "*example:*```${clearMessage(info.example)}```"


    private fun clearMessage(message: String)
            = message.replace("[", "")
                     .replace("]", "")
                     .replace("\n", "")

    private fun sendMessage(update: Update, text: String)
        = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}

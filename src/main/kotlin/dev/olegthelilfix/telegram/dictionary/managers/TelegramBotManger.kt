package dev.olegthelilfix.telegram.dictionary.managers

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryClient
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWordDescription
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
final class TelegramBotManger
@Autowired constructor(private var topWordManager: TopWordManager) : TelegramLongPollingBot(ApiContext.getInstance(DefaultBotOptions::class.java))
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
            val message = update.message.text

            if (isCommand(message)) {
                val args: List<String> = splitCommand(message)

                if (args[0].equals("/help", true) || args[0].equals("/start", true)) {
                    sendMessage(update, "Hi bro.\n*/topList* - `список топ слов`\n" +
                            "*/topList N* - `Значение слова из топ списка под номером N (0-29)`\n" +
                            "*/all AnyWord* - `Все доступные значения по указаному слову`\n" +
                            "*/best AnyWord* - `Первое значения по указаному слову`")
                }
                else if (args[0].equals("/pain", true)) {
                    sendMessage(update, "${update.message.from.firstName} да пошел бы ты нахуй, бич!")
                }
                else if (args[0].equals("/topList", true) && topWordManager.topWords.isNotEmpty()) {
                    if (args.size > 1) {
                        val number: Int = args[1].toInt()

                        sendMessage(update, formWordDescriptionText(topWordManager.topWords[number], urbanDictionaryBestResult(topWordManager.topWords[number])))
                    }
                    else {
                        sendMessage(update, formBestWordText(topWordManager.topWords))
                    }
                }
                else if (args[0].equals("/all", true) && args.size > 1) {
                    val word = args[1]

                    urbanDictionaryClient.findWorld(word).list.forEach { sendMessage(update, formWordDescriptionText(word, it)) }
                }
                else if (args[0].equals("/best", true) && args.size > 1) {
                    val word = args[1]

                    sendMessage(update, formWordDescriptionText(word, urbanDictionaryBestResult(word)))
                }
                else {
                    sendMessage(update, "моя твоя не понимай. писать по руски тогда я понимай.")
                }
            }
            else {
                sendMessage(update, formWordDescriptionText(message, urbanDictionaryBestResult(update.message.text)))
            }
        }
        catch (e: Exception) {
            sendMessage(update, "птчк вс очн плх.\n$e")
        }
    }

    override fun getBotUsername() = botUserName

    override fun getBotToken() = token

    private fun splitCommand(message: String) = message.split(" ")

    private fun isCommand(message: String) = message[0] == '/'

    private fun urbanDictionaryBestResult(word: String): UrbanDictionaryWordDescription
            = urbanDictionaryClient.findWorld(word).list[0]

    private fun formWordDescriptionText(message: String, info: UrbanDictionaryWordDescription): String
            = "*$message* _from UrbanDictionary_\n" +
              "*definition:*\n`${clearMessage(info.definition)}`\n" +
              "*example:*```${clearMessage(info.example)}```\n" +
              "[link](${info.permalink}) "

    private fun formBestWordText(words: List<String>): String {
        var result = ""

        words.forEachIndexed {index, element -> result += "$index. *$element*\n"}

        return result
    }

    private fun clearMessage(message: String)
            = message.replace("[", "")
                     .replace("]", "")
                     .replace("\n", "")

    private fun sendMessage(update: Update, text: String)
        = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}

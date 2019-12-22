package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.conf.settings.TelegramBotSettings
import dev.olegthelilfix.telegram.dictionary.models.ServiceUser
import dev.olegthelilfix.telegram.dictionary.repositories.ServiceUserRepository
import org.apache.log4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

@Service
final class TelegramBotService(private val telegramBotSettings: TelegramBotSettings,
                               private val telegramOperationService: TelegramOperationService,
                               private val serviceUserRepository: ServiceUserRepository) : TelegramLongPollingBot() {

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
            if (serviceUserRepository.isUserExist(update.message.from.id)) {
                val user = serviceUserRepository.save(ServiceUser(update.message.from, update.message.chatId))
                logger.info("User create {}", user.toString())
            }
//            else {
//                val user = serviceUserRepository.findByTelegramId(update.message.from.id)
//                logger.info("User found {}", user.toString());
//            }

            val args: List<String> = splitCommand(update.message.text)

            telegramOperationService.executeOperation(args).forEach { sendMessage(update, it) }
        } catch (e: Exception) {
            sendMessage(update, "птчк вс очн плх.\n$e")
        }
    }

    override fun getBotUsername() = telegramBotSettings.botUserName

    override fun getBotToken() = telegramBotSettings.token

    private fun splitCommand(message: String) = message.split(" ")

    private fun sendMessage(update: Update, text: String) = execute<Message, SendMessage>(SendMessage(update.message.chatId, text).setParseMode("Markdown"))
}


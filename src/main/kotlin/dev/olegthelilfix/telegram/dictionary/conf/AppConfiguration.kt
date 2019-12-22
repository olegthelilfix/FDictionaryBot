package dev.olegthelilfix.telegram.dictionary.conf

import dev.olegthelilfix.telegram.dictionary.services.operations.Operation
import dev.olegthelilfix.telegram.dictionary.services.operations.defoult.HelpOperation
import dev.olegthelilfix.telegram.dictionary.services.operations.defoult.StartOperation
import dev.olegthelilfix.telegram.dictionary.services.TelegramOperationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {
    @Autowired
    private lateinit var telegramOperation : MutableList<Operation>

    @Bean
    fun createTelegramOperation() : TelegramOperationService {
        telegramOperation.add(HelpOperation(telegramOperation))
        // Добавляем операцию start после операции help, для того что бы в ней была инфа о команде помощи
        telegramOperation.add(StartOperation(telegramOperation))

        return TelegramOperationService(telegramOperation)
    }
}

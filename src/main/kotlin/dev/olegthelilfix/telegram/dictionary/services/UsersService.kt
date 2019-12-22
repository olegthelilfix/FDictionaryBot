package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.models.ServiceUser
import dev.olegthelilfix.telegram.dictionary.repositories.ServiceUserRepository
import org.springframework.stereotype.Service
import org.telegram.telegrambots.api.objects.Update

@Service
class UsersService (private val serviceUserRepository: ServiceUserRepository) {
    fun loadOrCreateUser(update: Update): ServiceUser {
        if (!serviceUserRepository.isUserExist(update.message.from.id)) {
            return serviceUserRepository.save(ServiceUser(update.message.from, update.message.chatId))
        }

        return serviceUserRepository.findByTelegramId(update.message.from.id)
    }
}
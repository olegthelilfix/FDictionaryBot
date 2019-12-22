package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.models.UserWords
import dev.olegthelilfix.telegram.dictionary.repositories.UserWorldsRepository
import org.springframework.stereotype.Service

@Service
class UserWordsService(private val userWorldsRepository: UserWorldsRepository) {
    fun insertWord(userId: Long, word: String): Boolean {
        if (!userWorldsRepository.isWordExist(userId, word)) {
            userWorldsRepository.save(UserWords(userId, word))
            return true;
        }

        return false
    }
}
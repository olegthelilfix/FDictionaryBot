package dev.olegthelilfix.telegram.dictionary.repositories

import dev.olegthelilfix.telegram.dictionary.models.ServiceUser
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ServiceUserRepository: CrudRepository<ServiceUser, Long> {
    @Query("SELECT id, telegramId, chatId, firstName, lastName, userName, languageCode FROM ServiceUser WHERE telegramId = :telegramId")
    fun findByTelegramId(telegramId: Int) : ServiceUser

    @Query("SELECT (count(id) > 0) as isExist FROM ServiceUser WHERE telegramId = :telegramId")
    fun isUserExist(telegramId: Int): Boolean
}
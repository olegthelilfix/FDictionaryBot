package dev.olegthelilfix.telegram.dictionary.repositories

import dev.olegthelilfix.telegram.dictionary.models.UserWords
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserWorldsRepository: CrudRepository<UserWords, Long> {
    @Query("SELECT (count(id) > 0) as isExist FROM UserWords WHERE userId=:userId AND word=:word")
    fun isWordExist(userId: Long, word: String): Boolean
}
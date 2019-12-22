package dev.olegthelilfix.telegram.dictionary.services.operations.personal.dictionary

import dev.olegthelilfix.telegram.dictionary.services.UserWordsService
import dev.olegthelilfix.telegram.dictionary.services.UsersService
import dev.olegthelilfix.telegram.dictionary.services.operations.Operation
import dev.olegthelilfix.telegram.dictionary.shared.TelegramOperationArguments
import org.springframework.stereotype.Component

@Component
class AddNewWorldOperation (private val usersService: UsersService,
                            private val userWordsService: UserWordsService): Operation {
    override fun execute(args: TelegramOperationArguments): List<String> {
        val user = usersService.loadOrCreateUser(args.update)

        if(args.list.size > 1) {
            val word = args.list[1]

            if (userWordsService.insertWord(user.id, word)) {
                return listOf("В словарь добавленно новое слово $word")
            }
        }

        return listOf("Добавляемое слово уже существует")
    }

    override fun getName() = "/AddWorld"

    override fun getDescription() = "Добавляет новое слово в персональный словарь"
}
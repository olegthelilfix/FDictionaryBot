package dev.olegthelilfix.telegram.dictionary.operations

import org.springframework.stereotype.Component

@Component
class PainOperation: Operation {
    override fun execute(args: List<String>): List<String> = listOf("Эй ты, да ты, да пошел бы ты нахуй, бич!")
    override fun getName(): String = "/pain"
    override fun getDescription(): String = "Сделай мне больно!"
}
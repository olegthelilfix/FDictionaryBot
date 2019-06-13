package dev.olegthelilfix.telegram.dictionary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TelegramDictionaryApplication

fun main(args: Array<String>) {
    runApplication<TelegramDictionaryApplication>(*args)
}
package dev.olegthelilfix.telegram.dictionary

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryMainPageParser
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TelegramDictionaryApplication

fun main(args: Array<String>) {
//    runApplication<TelegramDictionaryApplication>(*args)

    UrbanDictionaryMainPageParser().parse()
}
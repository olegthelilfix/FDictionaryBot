package dev.olegthelilfix.telegram.dictionary.services

import dev.olegthelilfix.telegram.dictionary.api.client.UrbanDictionaryMainPageParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class TopWordService(private val wordParser: UrbanDictionaryMainPageParser) {
    var topWords: List<String> = wordParser.parseTopList()

    init {
        Executors.newSingleThreadScheduledExecutor()
                 .scheduleWithFixedDelay(UpdateTask(), 12, 12, TimeUnit.HOURS)
    }

    inner class UpdateTask: Runnable {
        override fun run() {
            topWords = wordParser.parseTopList()
        }

    }

}
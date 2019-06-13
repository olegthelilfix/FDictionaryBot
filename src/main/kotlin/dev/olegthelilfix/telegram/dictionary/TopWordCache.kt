package dev.olegthelilfix.telegram.dictionary

import dev.olegthelilfix.telegram.dictionary.access.UrbanDictionaryMainPageParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopWordCache @Autowired constructor(wordParser: UrbanDictionaryMainPageParser) {
    var topWords: List<String> = wordParser.parseTopList()
}
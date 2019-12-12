package dev.olegthelilfix.telegram.dictionary.conf.settings

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:settings.properties")
class UrbanDictionarySettings {
    @Value("\${urbanDictionary.url}")
    lateinit var url: String
    @Value("\${urbanDictionary.mainPageUrl}")
    lateinit var mainPageUrl: String
    @Value("\${urbanDictionary.key}")
    lateinit var key: String
    @Value("\${urbanDictionary.host}")
    lateinit var host: String

    fun getHeaders() = mapOf(Pair("X-RapidAPI-Host", host), Pair("X-RapidAPI-Key", key))
}
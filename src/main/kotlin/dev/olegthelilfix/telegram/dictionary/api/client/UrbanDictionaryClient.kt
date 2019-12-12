package dev.olegthelilfix.telegram.dictionary.api.client

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.util.NameValuePair
import dev.olegthelilfix.telegram.dictionary.utils.executeWebRequest
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWorldList
import dev.olegthelilfix.telegram.dictionary.utils.createWebRequest
import dev.olegthelilfix.telegram.dictionary.conf.settings.UrbanDictionarySettings

class UrbanDictionaryClient constructor(private val urbanDictionarySettings: UrbanDictionarySettings) {
    private val mapper = ObjectMapper().registerModule(KotlinModule())
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

    private val webClient = WebClient()

    fun findWorld(word: String): UrbanDictionaryWorldList {
        val requestList = listOf(NameValuePair("term", word))

        webClient.use {
            val request = createWebRequest(
                    urbanDictionarySettings.url,
                    HttpMethod.GET,
                    urbanDictionarySettings.headers,
                    requestList)

            val response = it.executeWebRequest(request)

            return mapper.readValue(response.contentAsString, UrbanDictionaryWorldList::class.java)
        }
    }
}
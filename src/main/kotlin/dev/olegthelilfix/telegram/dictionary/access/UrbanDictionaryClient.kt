package dev.olegthelilfix.telegram.dictionary.access

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.util.NameValuePair
import dev.olegthelilfix.telegram.dictionary.utils.executeWebRequest
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWorldList
import dev.olegthelilfix.telegram.dictionary.utils.createWebRequest
import dev.olegthelilfix.telegram.settings.UrbanDictionarySettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UrbanDictionaryClient
@Autowired
constructor(var urbanDictionarySettings: UrbanDictionarySettings) {
    private val mapper = ObjectMapper().registerModule(KotlinModule())
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

    private val webClient = WebClient()

    fun findWorld(word: String): UrbanDictionaryWorldList {
        val requestList = listOf(NameValuePair("term", word))
        val response = webClient.executeWebRequest(createWebRequest(requestList))

        webClient.close()

        webClient.use {
            it.executeWebRequest(createWebRequest(requestList))
        }

        return mapRequestResult(response)
    }

    private fun createWebRequest(requestParameters: List<NameValuePair>)
            = createWebRequest(urbanDictionarySettings.url, HttpMethod.GET, urbanDictionarySettings.headers, requestParameters)

    private fun mapRequestResult(response: WebResponse)
            = mapper.readValue(response.contentAsString, UrbanDictionaryWorldList::class.java)
}
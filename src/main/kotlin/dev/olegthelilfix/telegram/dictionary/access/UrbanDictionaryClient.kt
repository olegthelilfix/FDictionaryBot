package dev.olegthelilfix.telegram.dictionary.access

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.util.NameValuePair
import dev.olegthelilfix.telegram.dictionary.utils.executeWebRequest
import dev.olegthelilfix.telegram.dictionary.shared.UrbanDictionaryWorldList
import dev.olegthelilfix.telegram.dictionary.utils.createWebRequest

class UrbanDictionaryClient {
    private val headers: Map<String, String> = mapOf(Pair("X-RapidAPI-Host", "mashape-community-urban-dictionary.p.rapidapi.com"),
                                                     Pair("X-RapidAPI-Key", "fea8b134f6msh8d7c8f8fd71001fp19303fjsnb7dc8ebd241d"))

    private val url = "https://mashape-community-urban-dictionary.p.rapidapi.com/define"

    private val mapper = ObjectMapper().registerModule(KotlinModule())
                                       .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

    private val webClient = WebClient()

    fun findWorld(word: String): UrbanDictionaryWorldList {
        val requestList = listOf(NameValuePair("term", word))
        val response = webClient.executeWebRequest(createWebRequest(requestList))

        webClient.close()

        return mapRequestResult(response)
    }

    private fun createWebRequest(requestParameters: List<NameValuePair>): WebRequest
            = createWebRequest(url, HttpMethod.GET, headers, requestParameters)

    private fun mapRequestResult(response: WebResponse): UrbanDictionaryWorldList
            = mapper.readValue(response.contentAsString, UrbanDictionaryWorldList::class.java)
}
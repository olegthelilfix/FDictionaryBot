package dev.olegthelilfix.telegram.dictionary.access

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.springframework.stereotype.Service

@Service
class UrbanDictionaryMainPageParser {
    private val webClient = WebClient()

    fun parseTopList (): List<String> {
        webClient.options.isJavaScriptEnabled = false
        val page = webClient.getPage<HtmlPage>("https://www.urbandictionary.com")
        webClient.close()

        return page.getElementById("columnist")
                   .getByXPath<HtmlElement>("child::ul//child::li//child::a")
                   .map { it.asText() }.toList()

    }
}
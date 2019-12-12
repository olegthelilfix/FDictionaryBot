package dev.olegthelilfix.telegram.dictionary.api.client

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlPage
import dev.olegthelilfix.telegram.dictionary.conf.settings.UrbanDictionarySettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UrbanDictionaryMainPageParser @Autowired constructor(val urbanDictionarySettings: UrbanDictionarySettings) {
    private val webClient = WebClient()

    init {
        webClient.options.isJavaScriptEnabled = false
    }

    fun parseTopList(): List<String> {
        webClient.use { it ->
            val page = it.getPage<HtmlPage>(urbanDictionarySettings.mainPageUrl)

            return page.getElementById("columnist")
                    .getByXPath<HtmlElement>("child::ul//child::li//child::a")
                    .map(HtmlElement::asText).toList()
        }
    }
}
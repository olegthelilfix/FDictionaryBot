package dev.olegthelilfix.telegram.dictionary.utils

import com.gargoylesoftware.htmlunit.HttpMethod
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.WebRequest
import com.gargoylesoftware.htmlunit.WebResponse
import com.gargoylesoftware.htmlunit.util.NameValuePair
import java.nio.charset.Charset

fun createWebRequest(url: String,
                     submitMethod: HttpMethod,
                     headers: Map<String, String> = mapOf(),
                     requestParameters: List<NameValuePair> = listOf()): WebRequest {
    val request = WebRequest(java.net.URL(url), submitMethod)

    request.charset = Charset.forName("UTF-8")
    request.additionalHeaders = headers
    request.requestParameters = requestParameters

    return request
}

fun WebClient.executeWebRequest(request: WebRequest): WebResponse
        = this.webConnection.getResponse(request)
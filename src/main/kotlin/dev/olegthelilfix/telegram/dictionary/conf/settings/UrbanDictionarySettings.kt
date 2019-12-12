package dev.olegthelilfix.telegram.dictionary.conf.settings

class UrbanDictionarySettings (val url: String, val mainPageUrl: String, key: String, host: String) {
    val headers: Map<String, String> = mapOf(Pair("X-RapidAPI-Host", host), Pair("X-RapidAPI-Key", key))
}
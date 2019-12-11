package dev.olegthelilfix.telegram.settings

class UrbanDictionarySettings (var url: String, key: String, host: String) {
    val headers: Map<String, String> = mapOf(Pair("X-RapidAPI-Host", host), Pair("X-RapidAPI-Key", key))
}
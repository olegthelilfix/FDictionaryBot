package dev.olegthelilfix.telegram.dictionary

data class UrbanDictionaryResult (val definition: String,
                                  val permalink: String,
                                  val thumbs_up: Float,
                                  var sound_urls: List<String>,
                                  val author: String,
                                  val word: String,
                                  val defid: Float,
                                  val current_vote: String,
                                  val written_on: String,
                                  val example: String,
                                  val thumbs_down: Float = 0.toFloat())
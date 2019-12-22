package dev.olegthelilfix.telegram.dictionary.services.operations

interface Operation {
    fun execute(args: List<String>) : List<String>
    fun getName() : String
    fun getDescription() : String
    fun error() : String = "птчк вс очн плх."
}
package ru.asmelnikov.dictionaryapp.data.dto

data class Meaning(
    val definitions: List<Definition>? = null,
    val partOfSpeech: String? = null
)
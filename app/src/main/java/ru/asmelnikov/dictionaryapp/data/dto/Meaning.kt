package ru.asmelnikov.dictionaryapp.data.dto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
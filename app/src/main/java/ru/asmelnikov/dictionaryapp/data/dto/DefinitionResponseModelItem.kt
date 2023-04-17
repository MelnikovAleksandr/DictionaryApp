package ru.asmelnikov.dictionaryapp.data.dto

data class DefinitionResponseModelItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)
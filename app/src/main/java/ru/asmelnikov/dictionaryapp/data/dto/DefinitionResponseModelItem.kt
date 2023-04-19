package ru.asmelnikov.dictionaryapp.data.dto

data class DefinitionResponseModelItem(
    val meanings: List<Meaning>? = null,
    val origin: String? = null,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>? = null,
    val word: String? = null
)
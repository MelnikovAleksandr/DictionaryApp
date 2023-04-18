package ru.asmelnikov.dictionaryapp.data.dto

data class DefinitionResponseModelItem(
    val meanings: List<Meaning>? = emptyList(),
    val origin: String? = "",
    val phonetic: String? = "",
    val phonetics: List<Phonetic>? = emptyList(),
    val word: String? = ""
)
package ru.asmelnikov.dictionaryapp.data.dto

data class ErrorResponse(
    val message: String,
    val resolution: String,
    val title: String
)
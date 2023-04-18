package ru.asmelnikov.dictionaryapp.common

sealed class UiEvents {
    data class SnackBarEvent(val message: String) : UiEvents()
}

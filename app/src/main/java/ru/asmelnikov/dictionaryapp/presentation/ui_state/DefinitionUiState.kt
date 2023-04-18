package ru.asmelnikov.dictionaryapp.presentation.ui_state

import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModelItem

data class DefinitionUiState(
    val definition: List<DefinitionResponseModelItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

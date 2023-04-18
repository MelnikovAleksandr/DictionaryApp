package ru.asmelnikov.dictionaryapp.presentation.ui_state

import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModel

data class DefinitionUiState(
    val definition: List<DefinitionResponseModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

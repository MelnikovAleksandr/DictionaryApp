package ru.asmelnikov.dictionaryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.asmelnikov.dictionaryapp.common.Resource
import ru.asmelnikov.dictionaryapp.common.UiEvents
import ru.asmelnikov.dictionaryapp.domain.DefinitionRepository
import ru.asmelnikov.dictionaryapp.presentation.ui_state.DefinitionUiState
import javax.inject.Inject

@HiltViewModel
class DefinitionViewModel @Inject constructor(
    private val repository: DefinitionRepository
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinitionUiState())
    val definitionUiState: StateFlow<DefinitionUiState> = _definitionUiState.asStateFlow()

    private val _typedWord = mutableStateOf("")
    val typedWord: State<String> = _typedWord

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun setTypedWord(typedWord: String) {
        _typedWord.value = typedWord
    }

    fun getDefinition() {
        _definitionUiState.value = definitionUiState.value.copy(
            isLoading = true
        )

        val word = typedWord.value

        if (word.isNotEmpty()) {
            viewModelScope.launch {
                repository.getDefinition(word).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = emptyList()
                            )
                            _eventFlow.emit(
                                UiEvents.SnackBarEvent(
                                    message = response.message ?: "Something went wrong!"
                                )
                            )
                        }
                        is Resource.Success -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = response.data
                            )
                        }
                        else -> definitionUiState
                    }
                }
            }
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        viewModelScope.launch {
            _definitionUiState.value = definitionUiState.value.copy(
                isLoading = false
            )
            _eventFlow.emit(
                UiEvents.SnackBarEvent("Please enter a word!")
            )
        }
    }
}
package ru.asmelnikov.dictionaryapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.asmelnikov.dictionaryapp.common.UiEvents
import ru.asmelnikov.dictionaryapp.data.dto.Meaning
import ru.asmelnikov.dictionaryapp.presentation.components.*
import ru.asmelnikov.dictionaryapp.presentation.ui_state.DefinitionUiState
import ru.asmelnikov.dictionaryapp.ui.theme.DictionaryAppTheme

@Composable
fun DefinitionScreen(viewModel: DefinitionViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    val definitionUiState = viewModel.definitionUiState.collectAsState().value

    val typedWord = viewModel.typedWord.value

    val definitions =
        if (definitionUiState.definition?.isNotEmpty() == true)
            definitionUiState.definition[0].meanings ?: emptyList()
        else emptyList()

    DictionaryAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily.Default,
                                        color = Color.White
                                    )
                                ) {
                                    append("Your\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily.Default,
                                        color = Color.White
                                    )
                                ) {
                                    append("Dictionary")
                                }
                            }
                        )
                    },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            }
        ) { paddingValues ->

            DefinitionContent(
                definitionUiState = definitionUiState,
                typedWord = typedWord,
                setWordToBeSearched = { word ->
                    viewModel.setTypedWord(word)
                },
                searchWord = {
                    viewModel.getDefinition()
                },
                meanings = definitions,
                paddingValues = paddingValues
            )

        }
    }
}

@Composable
fun DefinitionContent(
    definitionUiState: DefinitionUiState,
    typedWord: String,
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    meanings: List<Meaning>,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(14.dp)
        ) {
            item {
                SearchTextFieldComponent(
                    setWordToBeSearched = setWordToBeSearched,
                    searchWord = searchWord,
                    typedWord = typedWord
                )
            }
            if (definitionUiState.isLoading || definitionUiState.definition?.isEmpty() == true) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingComponent(isLoading = definitionUiState.isLoading)
                        EmptyComponent(
                            isLoading = definitionUiState.isLoading,
                            definition = definitionUiState.definition
                        )
                    }
                }
            }
            if (!definitionUiState.isLoading && !definitionUiState.definition.isNullOrEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(15.dp))

                    PronunciationComponent(
                        word = definitionUiState.definition[0].word ?: "",
                        phonetic = definitionUiState.definition[0].phonetic ?: "---"
                    )
                }

                items(meanings) { meaning ->
                    Spacer(modifier = Modifier.height(10.dp))

                    PartsOfSpeechDefinitionComponent(
                        partOfSpeech = meaning.partOfSpeech ?: "",
                        definition = meaning.definitions ?: emptyList()
                    )
                }
            }
        }
    }
}




















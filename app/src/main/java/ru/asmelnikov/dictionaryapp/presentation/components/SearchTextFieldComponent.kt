package ru.asmelnikov.dictionaryapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import ru.asmelnikov.dictionaryapp.R
import ru.asmelnikov.dictionaryapp.ui.theme.Shapes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    typedWord: String
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = typedWord,
        onValueChange = { wordEntered ->
            setWordToBeSearched(wordEntered)
        },
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "SearchTextField" },
        placeholder = {
            Text("Search here")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (typedWord.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier.clickable {
                        setWordToBeSearched("")
                    }
                )
            }
        },
        shape = Shapes.small,
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color(R.color.light_gray),

                unfocusedIndicatorColor = Color(R.color.light_gray),

                focusedIndicatorColor = Color(R.color.light_blue)
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchWord()

                keyboardController?.hide()

                focusManager.clearFocus()
            }
        )
    )
}
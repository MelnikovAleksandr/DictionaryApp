package ru.asmelnikov.dictionaryapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.asmelnikov.dictionaryapp.data.dto.Definition

@Composable
fun PartsOfSpeechDefinitionComponent(
    partOfSpeech: String,
    definition: List<Definition>?
) {
    Column {
        PartsOfSpeechComponent(
            headerText = partOfSpeech,
            size = definition?.size ?: 0,
            color = Color(0XFF4C7AF2)
        )
        definition?.forEachIndexed { index, meaning ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Serif,
                            color = Color.Black
                        )
                    ) {
                        append("${index + 1}. ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily.Default
                        )
                    ) {
                        append(meaning.definition ?: "")
                    }
                },
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}
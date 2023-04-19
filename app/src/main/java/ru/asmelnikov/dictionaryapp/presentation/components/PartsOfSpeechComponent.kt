package ru.asmelnikov.dictionaryapp.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PartsOfSpeechComponent(
    headerText: String,
    size: Int,
    color: Color
) {
    Button(
        onClick = {  },
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                ) {
                    append(headerText)
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                ) {
                    append(" ($size)")
                }
            }
        )
    }
}
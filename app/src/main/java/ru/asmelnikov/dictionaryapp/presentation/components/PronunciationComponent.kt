package ru.asmelnikov.dictionaryapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.asmelnikov.dictionaryapp.R

@Composable
fun PronunciationComponent(word: String, phonetic: String) {
    Column {
        Text(
            text = word,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = phonetic,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(R.color.light_blue)
        )
    }
}
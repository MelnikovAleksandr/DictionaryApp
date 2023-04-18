package ru.asmelnikov.dictionaryapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.asmelnikov.dictionaryapp.R
import ru.asmelnikov.dictionaryapp.data.dto.DefinitionResponseModelItem

@Composable
fun EmptyComponent(isLoading: Boolean, definition: List<DefinitionResponseModelItem>?) {
    if (!isLoading && definition.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                EmptyAnimation()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Definition is not found...",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun EmptyAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        iterations = LottieConstants.IterateForever
    )
}

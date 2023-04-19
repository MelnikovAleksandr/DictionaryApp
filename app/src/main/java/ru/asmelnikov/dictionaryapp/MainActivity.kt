package ru.asmelnikov.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.asmelnikov.dictionaryapp.presentation.DefinitionScreen
import ru.asmelnikov.dictionaryapp.ui.theme.DictionaryAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            DictionaryAppTheme {
                DefinitionScreen()
            }
        }
    }
}

package com.patan.quranpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.patan.quranpro.presentation.AppScreen
import com.patan.quranpro.presentation.AppScreenViewModel
import com.patan.quranpro.ui.theme.QuranTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AppScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        processSplashScreen()
        setContent {
            QuranTheme {
                AppScreen(viewModel = viewModel)
            }
        }
    }

    private fun processSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.state.value.keepSplashScreenOn }
        }
    }
}




package com.patan.myhobbies.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = TextOnPrimary,
    secondary = NeutralGray,
    background = AppBackground,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    onBackground = TextPrimary,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue, // Using a darker blue for dark theme
    onPrimary = TextOnPrimary,
    secondary = LightGray,
    background = Color(0xFF121212), // A common dark theme background
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),
    onBackground = Color(0xFFE0E0E0),
)

object MyHobbiesTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography
}

@Composable
fun MyHobbiesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set the status bar color to match the background
            window.statusBarColor = colorScheme.background.toArgb()
            // Set the status bar icons to be light or dark based on the theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
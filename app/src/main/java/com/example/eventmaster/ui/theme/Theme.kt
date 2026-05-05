package com.example.eventmaster.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val EventMasterColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = TextPrimary,
    primaryContainer = TextPrimary,
    inversePrimary = AccentOrange,
    secondary = PrimaryMedium,
    onSecondary = TextPrimary,
    background = PrimaryDark,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    error = ErrorRed,
    onError = TextPrimary
)

@Composable
fun EventMasterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EventMasterColorScheme,
        typography = Typography,
        content = content
    )
}
package com.example.eventmaster.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.eventmaster.R

@Composable
fun EventMasterTheme(dynamicColor: Boolean = false, content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = colorResource(id = R.color.on_primary_container_light),
        onPrimary = colorResource(id = R.color.on_primary_light),
        primaryContainer = colorResource(id = R.color.primary_container_light),
        onPrimaryContainer = colorResource(id = R.color.on_primary_container_light),
        secondary = colorResource(id = R.color.secondary_light),
        background = colorResource(id = R.color.background_light),
        surface = colorResource(id = R.color.surface_light),
        inversePrimary = colorResource(id = R.color.inverse_primary_light)
    )

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
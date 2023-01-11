package com.ilhomsoliev.noteapp.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BLUE800,
    primaryVariant = BLUE900,
    secondary = GRAY400,
    secondaryVariant = GRAY500,
    background = Color.Black,
    surface = GRAY700,
    error = RED700,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White,
)

private val LightColorPalette = lightColors(
    primary = BLUE800,
    primaryVariant = BLUE900,
    secondary = GRAY400,
    secondaryVariant = GRAY500,
    background = Color.White,
    surface = GRAY700,
    error = RED700,
    onPrimary = Color.White,
    onSecondary = Color.White   ,
    onBackground = Color.Black,
    onSurface = Color.Gray,
    onError = Color.White,
    )

@Composable
fun NoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
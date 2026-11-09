package com.naiane.privaci.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeonGreen = Color(0xFF39FF14)
val DarkGray = Color(0xFF1C1C1C)

private val Colors = darkColorScheme(
    primary = NeonGreen,
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = DarkGray,
    onSurface = Color.White
)

@Composable
fun PrivaciTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, content = content)
}

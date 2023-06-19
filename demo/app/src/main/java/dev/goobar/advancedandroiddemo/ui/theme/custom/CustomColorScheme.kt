package dev.goobar.advancedandroiddemo.ui.theme.custom

import androidx.compose.ui.graphics.Color
import dev.goobar.advancedandroiddemo.ui.theme.Blue

data class CustomColorScheme(
    val primary: Color
)

fun defaultCustomColorScheme() = CustomColorScheme(
    primary = Blue
)
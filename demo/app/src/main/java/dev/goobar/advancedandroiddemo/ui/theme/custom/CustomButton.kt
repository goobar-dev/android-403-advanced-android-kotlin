package dev.goobar.advancedandroiddemo.ui.theme.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * A custom Button that doesn't rely on the Material Design Components
 *
 * This demonstrates the basic building blocks that can be used to create a fully
 * custom design system in Jetpack Compose.
 */
@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color)
            .clip(RectangleShape)
            .defaultMinSize(minHeight = 48.dp)
            .clickable(
                enabled = true,
                onClickLabel = null,
                role = Role.Button,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
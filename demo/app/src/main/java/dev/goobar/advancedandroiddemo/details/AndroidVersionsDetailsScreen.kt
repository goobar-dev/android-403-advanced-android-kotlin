package dev.goobar.advancedandroiddemo.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo

@Composable
internal fun AndroidVersionDetailsScreen(info: AndroidVersionInfo, onBack: () -> Unit) {
    BackHandler(enabled = true, onBack = onBack)
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = info.publicName, style = MaterialTheme.typography.displayMedium)
        Text(text = "${info.codename} - API ${info.apiVersion}", style = MaterialTheme.typography.headlineSmall)
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = info.details,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}
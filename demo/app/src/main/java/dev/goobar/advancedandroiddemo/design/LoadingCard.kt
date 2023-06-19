package dev.goobar.advancedandroiddemo.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun LoadingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .placeholder(true, highlight = PlaceholderHighlight.fade()),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "placeholder", style = MaterialTheme.typography.displaySmall)
            Text(text = "placeholder", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "placeholder", style = MaterialTheme.typography.labelLarge)
        }
    }
}
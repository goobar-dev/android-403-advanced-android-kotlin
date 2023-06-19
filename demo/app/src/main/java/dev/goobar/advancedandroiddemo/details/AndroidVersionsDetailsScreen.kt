@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.R

@Composable
internal fun AndroidVersionDetailsScreen(
    viewModel: AndroidVersionDetailsViewModel,
    onBack: () -> Unit
) {
    val state: AndroidVersionDetailsViewModel.State by viewModel.state.collectAsState()

    BackHandler(enabled = true, onBack = onBack)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = state.title, modifier = Modifier.padding(start = 20.dp)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back arrow"
                        )
                    }
                }
            )

        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = state.title, style = MaterialTheme.typography.displayMedium)
                Text(
                    text = state.subtitle,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = state.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
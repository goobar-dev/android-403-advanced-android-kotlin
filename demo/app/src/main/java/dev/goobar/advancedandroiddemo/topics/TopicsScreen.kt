@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.topics

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
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsViewModel

@Composable
internal fun AndroidTopicsScreen(
    viewModel: AndroidTopicsViewModel = viewModel(),
    onBack: () -> Unit
) {
    //BackHandler(enabled = true, onBack = onBack)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Topics", modifier = Modifier.padding(start = 20.dp)) }
            )

        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroiddemo.design.LoadingCard

@Composable
internal fun AndroidTopicsScreen(
    viewModel: AndroidTopicsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val topics by viewModel.topics.collectAsState()
    val snackbarHostState = remember{ SnackbarHostState() }
    val layoutDirection = LocalLayoutDirection.current

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is AndroidTopicsViewModel.Events.ShowTopicClickedMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hello Advanced Android") })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(1f),
            contentPadding = PaddingValues(
                start = 20.dp + paddingValues.calculateStartPadding(layoutDirection),
                top = 20.dp + paddingValues.calculateTopPadding(),
                end = 20.dp + paddingValues.calculateEndPadding(layoutDirection),
                bottom = 20.dp + paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = spacedBy(16.dp)
        ) {
            if (topics.isEmpty()) {
                items(10) {
                    LoadingCard()
                }
            } else {
                items(topics) { topic ->
                    TopicCard(topic, viewModel::onTopicClicked)
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: AndroidTopicsViewModel.TopicViewItem, onClick: (AndroidTopicsViewModel.TopicViewItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clickable { onClick(topic) }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = topic.title, style = MaterialTheme.typography.displaySmall)
            Text(text = topic.categories.joinToString(", "), style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = topic.content, style = MaterialTheme.typography.labelLarge)
        }
    }
}
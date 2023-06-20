@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroiddemo.trending

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.goobar.advancedandroiddemo.design.LoadingCard

@Composable
fun TrendingReposScreen(onBackClick: () -> Unit, viewModel: TrendingReposViewModel = hiltViewModel()) {

    val repos by viewModel.repos.collectAsState()
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Hello Advanced Android") }) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.fillMaxSize(1f),
            contentPadding = PaddingValues(
                start = 20.dp + paddingValues.calculateStartPadding(layoutDirection),
                top = 20.dp + paddingValues.calculateTopPadding(),
                end = 20.dp + paddingValues.calculateEndPadding(layoutDirection),
                bottom = 20.dp + paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
        ) {
            if (repos.isEmpty()) {
                items(10) { LoadingCard() }
            } else {
                items(repos, key = { it.name }) { repo ->
                    RepoCard(repo)
                }
            }
        }

    }
}

@Composable
fun RepoCard(repo: TrendingReposViewModel.FeaturedRepoViewItem) {
    Card(
        modifier = Modifier.fillMaxWidth(1f),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = repo.displayName, style = MaterialTheme.typography.displaySmall)
            Text(text = "@${repo.createdBy}", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = repo.description, style = MaterialTheme.typography.labelLarge)
        }
    }
}
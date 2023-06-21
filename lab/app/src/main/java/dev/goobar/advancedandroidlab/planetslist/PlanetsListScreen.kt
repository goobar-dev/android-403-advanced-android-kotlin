@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroidlab.planetslist

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.goobar.advancedandroidlab.domain.Planet
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PlanetsListScreen(
    viewModel: PlanetsListViewModel = hiltViewModel(),
    onClick: (Planet) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Star Wars Planets") })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            if (state.planets.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.size(56.dp))
            } else {
                PlanetsList(planets = state.planets, onClick = onClick)
            }
        }
    }
}

@Composable
private fun PlanetsList(planets: ImmutableList<Planet>, onClick: (Planet) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("Planets List"),
        verticalArrangement = spacedBy(8.dp),
    ) {
        items(planets) { planet ->
            PlanetListItem(planet = planet, onClick = onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlanetListItem(planet: Planet, onClick: (Planet) -> Unit) {
    Card(onClick = { onClick(planet) }, modifier = Modifier.fillMaxWidth(1f)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(planet.name)
        }
    }
}
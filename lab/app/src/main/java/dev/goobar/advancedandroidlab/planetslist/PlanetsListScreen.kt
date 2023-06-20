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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroidlab.domain.Planet

@Composable
fun PlanetsListScreen(planets: List<Planet>, onClick: (Planet) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Star Wars Planets") })
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            if (planets.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.size(56.dp))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(20.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = spacedBy(8.dp)
                ) {
                    items(planets) { planet ->
                        PlanetListItem(planet = planet, onClick = onClick)
                    }
                }
            }
        }
    }
}

@Composable
private fun PlanetListItem(planet: Planet, onClick: (Planet) -> Unit) {
    Card(onClick = { onClick(planet) }, modifier = Modifier.fillMaxWidth(1f)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(planet.name)
        }
    }
}
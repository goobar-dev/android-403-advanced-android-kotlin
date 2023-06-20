@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.advancedandroidlab.details

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.goobar.advancedandroidlab.R
import dev.goobar.advancedandroidlab.domain.Planet

@Composable
fun PlanetDetailsScreen(planet: Planet, onBackClick: () -> Unit) {
    BackHandler(true, onBackClick)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(planet.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                }
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = planet.name, style = MaterialTheme.typography.displayMedium)
                Text(text = "Population: ${planet.population}", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

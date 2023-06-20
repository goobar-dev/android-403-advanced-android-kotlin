package dev.goobar.advancedandroidlab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.advancedandroidlab.details.PlanetDetailsScreen
import dev.goobar.advancedandroidlab.domain.toPlanet
import dev.goobar.advancedandroidlab.network.PlanetDTO
import dev.goobar.advancedandroidlab.network.SWAPINetworkClient
import dev.goobar.advancedandroidlab.planetslist.PlanetsListScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
internal fun StarWarsPlanetsNavigationGraph() {
    val navController = rememberNavController()

    var planets by remember { mutableStateOf<List<PlanetDTO>>(emptyList()) }
    LaunchedEffect(Unit) {
        launch(Dispatchers.IO) {
            planets = SWAPINetworkClient.getPlanets().results
        }
    }

    NavHost(navController = navController, startDestination = StarWarsPlanetsNavigationDestinations.PlanetsList.route) {
        composable(StarWarsPlanetsNavigationDestinations.PlanetsList.route) {
            PlanetsListScreen(planets.map { it.toPlanet() }) { planet ->
                navController.navigate(StarWarsPlanetsNavigationDestinations.PlanetDetails.createRouteWithArgs(planet))
            }
        }
        composable(StarWarsPlanetsNavigationDestinations.PlanetDetails.route) { entry ->
            val planet = StarWarsPlanetsNavigationDestinations.PlanetDetails.retrieveArgs(entry.arguments)
            PlanetDetailsScreen(planet) {
                navController.popBackStack()
            }
        }
    }
}
package dev.goobar.advancedandroidlab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.advancedandroidlab.details.PlanetDetailsScreen
import dev.goobar.advancedandroidlab.planetslist.PlanetsListScreen

@Composable
internal fun StarWarsPlanetsNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StarWarsPlanetsNavigationDestinations.PlanetsList.route) {
        composable(StarWarsPlanetsNavigationDestinations.PlanetsList.route) {
            PlanetsListScreen() { planet ->
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
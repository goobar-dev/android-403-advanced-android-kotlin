package dev.goobar.advancedandroiddemo.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsScreen
import dev.goobar.advancedandroiddemo.versions.AndroidVersionsListScreen

@Composable
internal fun DemoNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DemoNavigationDestinations.VersionsList.route) {
        composable(DemoNavigationDestinations.VersionsList.route) {
            AndroidVersionsListScreen { info ->
                navController.navigate(DemoNavigationDestinations.VersionDetails.createRouteWithArgs(info))
            }
        }
        composable(route = DemoNavigationDestinations.VersionDetails.route) { entry ->
            val info = DemoNavigationDestinations.VersionDetails.retrieveArgs(entry.arguments)
            AndroidVersionDetailsScreen(info) {
                navController.popBackStack()
            }
        }
    }
}
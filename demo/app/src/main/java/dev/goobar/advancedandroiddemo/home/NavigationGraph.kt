package dev.goobar.advancedandroiddemo.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsScreen
import dev.goobar.advancedandroiddemo.versions.AndroidVersionsListScreen

@Composable
internal fun DemoNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "versionsList") {
        composable("versionsList") {
            AndroidVersionsListScreen { info ->
                navController.navigate("versionDetails/${info.apiVersion}")
            }
        }
        composable(
            route = "versionDetails/{apiVersion}",
            arguments = listOf(navArgument("apiVersion") { type = NavType.IntType })
        ) { entry ->
            val apiVersion = entry.arguments?.getInt("apiVersion") ?: throw IllegalStateException("missing apiVersion")
            AndroidVersionDetailsScreen(apiVersion) {
                navController.popBackStack()
            }
        }
    }
}
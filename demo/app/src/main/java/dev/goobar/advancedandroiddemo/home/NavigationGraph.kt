@file:OptIn(ExperimentalAnimationApi::class)

package dev.goobar.advancedandroiddemo.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsScreen
import dev.goobar.advancedandroiddemo.details.AndroidVersionDetailsViewModel
import dev.goobar.advancedandroiddemo.topics.AndroidTopicsScreen
import dev.goobar.advancedandroiddemo.versions.AndroidVersionsListScreen

@Composable
internal fun DemoNavigationGraph(navController: NavHostController) {

    AnimatedNavHost(
        navController = navController,
        startDestination = DemoNavigationDestinations.VersionsList.route
    ) {
        composable(route = DemoNavigationDestinations.VersionsList.route) {
            AndroidVersionsListScreen { info ->
                navController.navigate(
                    DemoNavigationDestinations.VersionDetails.createRouteWithArgs(
                        info
                    )
                )
            }
        }
        composable(route = DemoNavigationDestinations.Topics.route) {
            AndroidTopicsScreen() {
                navController.popBackStack()
            }
        }
        composable(
            route = DemoNavigationDestinations.VersionDetails.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                )
            },
        ) { entry ->
            val info = DemoNavigationDestinations.VersionDetails.retrieveArgs(entry.arguments)
            AndroidVersionDetailsScreen(AndroidVersionDetailsViewModel(info)) {
                navController.popBackStack()
            }
        }
    }
}
@file:OptIn(ExperimentalAnimationApi::class)

package dev.goobar.advancedandroiddemo.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.goobar.advancedandroiddemo.R
import dev.goobar.advancedandroiddemo.ui.theme.AdvancedAndroidDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationItems = mapOf(
        DemoNavigationDestinations.VersionsList to BottomNavItem("Versions", R.drawable.ic_info, DemoNavigationDestinations.VersionsList),
        DemoNavigationDestinations.Topics to BottomNavItem("Topics", R.drawable.ic_lightbulb, DemoNavigationDestinations.Topics),
        DemoNavigationDestinations.Notes to BottomNavItem("Notes", R.drawable.ic_notes, DemoNavigationDestinations.Notes),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()

            AdvancedAndroidDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Column(verticalArrangement = Arrangement.Bottom) {
                        Box(modifier = Modifier.weight(1f)) {
                            DemoNavigationGraph(navController)
                        }
                        DemoBottomNavigation(navController) { destination ->
                            navController.navigate(destination.route)
                        }
                    }

                }
            }
        }
    }

    @Composable
    private fun DemoBottomNavigation(
        navController: NavController,
        onClick: (NavigationDestination) -> Unit
    ) {
        var selected by remember { mutableStateOf(navigationItems[DemoNavigationDestinations.VersionsList]) }
        val destinationListener: NavController.OnDestinationChangedListener = remember {
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.route) {
                    DemoNavigationDestinations.Topics.route -> selected = navigationItems[DemoNavigationDestinations.Topics]
                    DemoNavigationDestinations.VersionsList.route -> selected = navigationItems[DemoNavigationDestinations.VersionsList]
                }
            }
        }
        DisposableEffect(key1 = navController) {
            navController.addOnDestinationChangedListener(destinationListener)
            onDispose { navController.removeOnDestinationChangedListener(destinationListener) }
        }

        NavigationBar() {
            navigationItems.values.forEach { navItem ->
                NavigationBarItem(
                    selected = (selected == navItem),
                    onClick = {
                        selected = navItem
                        onClick(navItem.destination)
                    },
                    icon = { Icon(painter = painterResource(navItem.icon), contentDescription = navItem.label) },
                    label = { Text(text = navItem.label, style = MaterialTheme.typography.labelSmall) },
                    alwaysShowLabel = true
                )
            }
        }
    }

    data class BottomNavItem(
        val label: String,
        @DrawableRes val icon: Int,
        val destination: NavigationDestination
    )
}
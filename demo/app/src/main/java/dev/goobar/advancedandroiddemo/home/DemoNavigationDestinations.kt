package dev.goobar.advancedandroiddemo.home

import dev.goobar.advancedandroiddemo.data.AndroidVersionInfo

object DemoNavigationDestinations {
    object VersionsList : NavigationDestination
    object VersionDetails : ArgumentDestination<AndroidVersionInfo>
    object Topics : NavigationDestination
    object Notes : NavigationDestination
    object AddNote : NavigationDestination
    object TrendingRepos : NavigationDestination
}
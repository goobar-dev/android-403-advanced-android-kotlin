package dev.goobar.advancedandroidlab.navigation

import dev.goobar.advancedandroidlab.domain.Planet

object StarWarsPlanetsNavigationDestinations {
    object PlanetsList : NavigationDestination
    object PlanetDetails : ArgumentDestination<Planet>
}
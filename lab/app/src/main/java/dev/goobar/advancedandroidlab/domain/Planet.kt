package dev.goobar.advancedandroidlab.domain

import dev.goobar.advancedandroidlab.db.PlanetEntity
import dev.goobar.advancedandroidlab.navigation.NavigationArgs
import dev.goobar.advancedandroidlab.network.PlanetDTO
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val rotationPeriod: Int = 0,
    val orbitalPeriod: Int = 0,
    val diameter: Int = 0,
    val climate: String = "",
    val gravity: String = "",
    val population: String = "",
    val url: String = ""
) : NavigationArgs

fun PlanetEntity.toPlanet() = Planet(
    name,
    rotationPeriod,
    orbitalPeriod,
    diameter,
    climate,
    gravity,
    population,
    url
)
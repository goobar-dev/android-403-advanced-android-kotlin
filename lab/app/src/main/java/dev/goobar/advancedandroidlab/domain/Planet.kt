package dev.goobar.advancedandroidlab.domain

import dev.goobar.advancedandroidlab.navigation.NavigationArgs
import dev.goobar.advancedandroidlab.network.PlanetDTO
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val population: String,
    val url: String
) : NavigationArgs

fun PlanetDTO.toPlanet() = Planet(
    name,
    rotationPeriod,
    orbitalPeriod,
    diameter,
    climate,
    gravity,
    population,
    url
)
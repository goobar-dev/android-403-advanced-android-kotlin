package dev.goobar.advancedandroidlab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDTO(
    val name: String,

    @SerialName("rotation_period")
    val rotationPeriod: Int,

    @SerialName("orbital_period")
    val orbitalPeriod: Int,

    val diameter: Int,

    val climate: String,

    val gravity: String,

    val population: String,

    val url: String
)
package dev.goobar.advancedandroidlab.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.goobar.advancedandroidlab.domain.Planet
import dev.goobar.advancedandroidlab.network.PlanetDTO

@Entity
data class PlanetEntity(
    @PrimaryKey
    val name: String,
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val population: String,
    val url: String
)

fun PlanetDTO.toEntity() = PlanetEntity(
    name,
    rotationPeriod,
    orbitalPeriod,
    diameter,
    climate,
    gravity,
    population,
    url
)
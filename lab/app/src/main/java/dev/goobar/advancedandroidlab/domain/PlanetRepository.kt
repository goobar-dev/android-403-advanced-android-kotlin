package dev.goobar.advancedandroidlab.domain

import dev.goobar.advancedandroidlab.network.SWAPINetworkClient

interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
}

object NetworkPlanetRepository: PlanetRepository {
    override suspend fun getPlanets(): List<Planet> {
        return SWAPINetworkClient.getPlanets().results.map { it.toPlanet() }
    }
}
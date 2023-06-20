package dev.goobar.advancedandroidlab.network

import kotlinx.serialization.Serializable

@Serializable
class PlanetsResponseDTO(val results: List<PlanetDTO>)
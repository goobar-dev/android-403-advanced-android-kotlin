package dev.goobar.advancedandroidlab.domain

import android.util.Log
import dev.goobar.advancedandroidlab.db.PlanetsDao
import dev.goobar.advancedandroidlab.db.toEntity
import dev.goobar.advancedandroidlab.network.SWAPINetworkClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PlanetRepository {

    suspend fun refresh()

    fun getPlanets(): Flow<List<Planet>>
}

class NetworkPlanetRepository @Inject constructor(private val planetsDao: PlanetsDao) : PlanetRepository {

    override suspend fun refresh() {
        try {
            planetsDao.save(SWAPINetworkClient.getPlanets().results.map { it.toEntity() })
        } catch (error: Throwable) {
            Log.d(NetworkPlanetRepository::class.simpleName, "Error fetching planets")
        }
    }

    override fun getPlanets(): Flow<List<Planet>> {
        return planetsDao.getAll().map { entities -> entities.map { entity -> entity.toPlanet() } }
    }
}
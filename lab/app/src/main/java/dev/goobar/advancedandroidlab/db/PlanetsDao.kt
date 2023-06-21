package dev.goobar.advancedandroidlab.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetsDao {

    @Insert(onConflict = REPLACE)
    suspend fun save(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planetentity")
    fun getAll(): Flow<List<PlanetEntity>>
}
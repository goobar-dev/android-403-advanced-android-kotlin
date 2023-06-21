package dev.goobar.advancedandroidlab.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [PlanetEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planetsDao(): PlanetsDao
}
package dev.goobar.advancedandroidlab.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.goobar.advancedandroidlab.db.AppDatabase
import dev.goobar.advancedandroidlab.db.PlanetsDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): PlanetsDao {
        return appDatabase.planetsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "star-wars-planets-database"
        ).build()
    }
}
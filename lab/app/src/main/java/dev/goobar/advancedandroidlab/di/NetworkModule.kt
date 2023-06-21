package dev.goobar.advancedandroidlab.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.goobar.advancedandroidlab.domain.NetworkPlanetRepository
import dev.goobar.advancedandroidlab.domain.PlanetRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideRepository(repository: NetworkPlanetRepository): PlanetRepository
}
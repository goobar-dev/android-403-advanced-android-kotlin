package dev.goobar.advancedandroidlab

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.HiltAndroidApp
import dev.goobar.advancedandroidlab.dailyplanet.DailyPlanetSyncWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class StarWarsPlanetsApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        val uploadWorkRequest: WorkRequest =
            PeriodicWorkRequestBuilder<DailyPlanetSyncWorker>(30, TimeUnit.SECONDS)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)
    }
}
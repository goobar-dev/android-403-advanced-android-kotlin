package dev.goobar.advancedandroiddemo

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.HiltAndroidApp
import dev.goobar.advancedandroiddemo.sync.FeaturedReposSyncWorker
import javax.inject.Inject

@HiltAndroidApp
class DemoApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<FeaturedReposSyncWorker>()
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)
    }
}
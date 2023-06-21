package dev.goobar.advancedandroidlab.dailyplanet

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.goobar.advancedandroidlab.MainActivity
import dev.goobar.advancedandroidlab.R
import dev.goobar.advancedandroidlab.domain.NetworkPlanetRepository
import dev.goobar.advancedandroidlab.domain.Planet
import kotlinx.coroutines.flow.first

@HiltWorker
class DailyPlanetSyncWorker @AssistedInject constructor(
    private val repository: NetworkPlanetRepository,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val planets = repository.getPlanets().first()
        if (planets.isEmpty()) return Result.success()
        showDailyPlanetNotification(planets.random())
        return Result.success()
    }

    private fun showDailyPlanetNotification(planet: Planet) {
        val notificationManager: NotificationManager = getSystemService(appContext, NotificationManager::class.java) as NotificationManager
        createNotificationChannel(notificationManager)

        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(appContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(appContext, "0")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Planet Of The Day")
            .setContentText(planet.name)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(appContext)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(0, builder.build())
            }
        }
    }

    private fun createNotificationChannel(manager: NotificationManager) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Planet Of The Day"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("0", name, importance)

            // Register the channel with the system

            manager.createNotificationChannel(channel)
        }
    }
}

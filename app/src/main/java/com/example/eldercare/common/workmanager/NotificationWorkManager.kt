package com.example.eldercare.common.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.eldercare.model.service.notifications.MealReminderService
import com.example.eldercare.model.service.notifications.MissedMealsAlertService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.TimeUnit

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val missedMealsAlertService: MissedMealsAlertService,
    private val mealReminderService: MealReminderService,
): CoroutineWorker(appContext, workerParams)  {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            Log.d("test", "NotificationWorker.doWork")
            missedMealsAlertService.alertCaregiversAboutConsecutivelyMissedMeals()
            mealReminderService.remindPatientAboutMealsThatAreDueToEat()
            Result.success()
        } catch (e: Exception) {
            Log.d("test", "doWork error" + e.toString())
            Result.retry()
        }
    }
}

fun setupRepeatingWork(context: Context) {
    Log.d("test", "setupRepeatingWork")
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED) // if network is required
        .build()

    try {
        val repeatingRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "MealCheckWork",
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    } catch (e: Exception) {
        Log.d("test","error during setupRepeatingWork" + e.toString())
    }
}
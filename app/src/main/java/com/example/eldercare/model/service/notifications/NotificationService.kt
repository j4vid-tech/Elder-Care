package com.example.eldercare.model.service.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.eldercare.ElderCareActivity
import com.example.eldercare.R
import com.example.eldercare.common.ext.getHoursAndMinutes
import com.example.eldercare.model.Meal
import com.example.eldercare.model.Patient

class NotificationService(
    private val context: Context,
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showMissedMealsAlertNotification(patient: Patient) {
        Log.d("test", "showMissedMealsAlertNotification")
        showNotification(
            MEAL_REMINDER_CHANNEL_ID,
            "${patient.fullName} has missed three consecutive meals!",
            "Please take appropriate actions!",
            Intent(context, ElderCareActivity::class.java)
        )
    }

    fun showMealReminderNotification(meal: Meal) {
        Log.d("test", "showMealReminderNotification")
        showNotification(
            MEAL_REMINDER_CHANNEL_ID,
            "Please eat your ${meal.type} scheduled at ${(meal.date?.getHoursAndMinutes() ?: "???")}",
            meal.dish,
            Intent(context, ElderCareActivity::class.java)
        )
        Log.d("test", "showMealReminderNotification FINISHED JOOOOOOOOOOOO")
    }
    fun showNotification(
        channelId: String,
        title: String = "",
        text: String = "",
        activityIndent: Intent = Intent(),
        smallIcon: Int = R.drawable.regular_notification,
    ) {
        Log.d("test", "showNotification")

        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIndent,
            PendingIntent.FLAG_IMMUTABLE,
        )
        Log.d("test", "after activityPendingIntent")
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(smallIcon)
            .setContentIntent(activityPendingIntent)
            .build()
        Log.d("test", "after notification")
        try {
            notificationManager.notify(1, notification)
            Log.d("test", "Notification send: $notification, ContentTitle: $title, ContentText: $text")
        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
        Log.d("test", "after notificationManager")
    }

    companion object {
        const val MEAL_REMINDER_CHANNEL_ID = "meal_reminder_channel"
        const val MISSED_MEALS_ALERT_CHANNEL_ID = "missed_meals_alert_channel"
    }
}
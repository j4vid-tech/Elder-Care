package com.example.eldercare.model.service.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

fun createNotificationChannels(context: Context) {
    val mealReminderChannel = NotificationChannel(
        NotificationService.MEAL_REMINDER_CHANNEL_ID,
        "Meal Reminder",
        NotificationManager.IMPORTANCE_DEFAULT
    )
    mealReminderChannel.description = "Reminds you to eat your meals."
    createNotificationChannel(context, mealReminderChannel)

    val missedMealsAlertChannel = NotificationChannel(
        NotificationService.MISSED_MEALS_ALERT_CHANNEL_ID,
        "Missed Meals Alert",
        NotificationManager.IMPORTANCE_HIGH
    )
    missedMealsAlertChannel.description =
        "Alerts you when a patient missed three consecutive meals."
    createNotificationChannel(context, missedMealsAlertChannel)
}

fun createNotificationChannel(context: Context, channel: NotificationChannel) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}
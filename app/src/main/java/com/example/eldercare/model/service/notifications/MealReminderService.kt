package com.example.eldercare.model.service.notifications

import android.content.Context
import android.util.Log
import com.example.eldercare.model.Meal
import com.example.eldercare.model.datastore.PatientUserInfoStore
import com.example.eldercare.model.repository.impl.MealRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.time.Duration
import java.time.Instant
import javax.inject.Inject

class MealReminderService @Inject constructor(
    private val mealRepository: MealRepository,
    private val patientUserInfoStore: PatientUserInfoStore,
    @ApplicationContext private val context: Context,
) {
    private val notificationService: NotificationService = NotificationService(context)
    suspend fun remindPatientAboutMealsThatAreDueToEat() {
        try {
            patientUserInfoStore.patientId().collect { value ->
                val allMeals = mealRepository.getAllByPatientId(value)

                remindPatientAboutMealsThatAreDueToEatInNext15Min(allMeals)
                remindPatientAboutMealsThatAreDueToEat45MinAgo(allMeals)
                remindPatientAboutMealsThatAreDueToEat90MinAgo(allMeals)
            }
        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
    }

    private suspend fun remindPatientAboutMealsThatAreDueToEatInNext15Min(allMeals: Flow<List<Meal>>) {
        allMeals.collect { meals ->
            for (meal in meals) {
                val mealDate = meal.date!!.toDate().toInstant()
                val currentTime = Instant.now()
                val inFifteenMinutes = currentTime.plus(Duration.ofMinutes(15))

                if (!meal.gotEaten && mealDate.isAfter(currentTime) && mealDate.isBefore(
                        inFifteenMinutes
                    )
                ) {
                    notificationService.showMealReminderNotification(meal)
                }
            }
        }
    }


    private suspend fun remindPatientAboutMealsThatAreDueToEat45MinAgo(allMeals: Flow<List<Meal>>) {
        allMeals.collect { meals ->
            for (meal in meals) {
                val mealDate = meal.date!!.toDate().toInstant()
                val currentTime = Instant.now()
                val lower = currentTime.minus(Duration.ofMinutes(30))
                val upper = currentTime.minus(Duration.ofMinutes(45))

                if (!meal.gotEaten && mealDate.isAfter(upper) && mealDate.isBefore(lower)) {
                    notificationService.showMealReminderNotification(meal)
                }
            }
        }
    }

    private suspend fun remindPatientAboutMealsThatAreDueToEat90MinAgo(allMeals: Flow<List<Meal>>) {
        allMeals.collect { meals ->
            for (meal in meals) {
                val mealDate = meal.date!!.toDate().toInstant()
                val currentTime = Instant.now()
                val lower = currentTime.minus(Duration.ofMinutes(75))
                val upper = currentTime.minus(Duration.ofMinutes(90))

                if (!meal.gotEaten && mealDate.isAfter(upper) && mealDate.isBefore(lower)) {
                    notificationService.showMealReminderNotification(meal)
                }
            }
        }
    }

}
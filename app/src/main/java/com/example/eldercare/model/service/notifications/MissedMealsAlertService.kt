package com.example.eldercare.model.service.notifications

import android.content.Context
import android.util.Log
import com.example.eldercare.common.ext.missedInLast15Minutes
import com.example.eldercare.model.Meal
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.impl.MealRepository
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.model.service.CaretakerAccountService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class MissedMealsAlertService @Inject constructor(
    private val mealRepository: MealRepository,
    private val patientRepository: PatientRepository,
    private val caretakerAccountService: CaretakerAccountService,
    @ApplicationContext private val context: Context,
) {
    private val notificationService: NotificationService = NotificationService(context)

    suspend fun alertCaregiversAboutConsecutivelyMissedMeals() {
        Log.d("test", "alertCaregiversAboutConsecutivelyMissedMeals")
        mealRepository.getAllInThePast().map { meals ->
            meals.filter { meal ->
                isMealOfMyPatient(meal)
                        && isThirdConsecutiveMissedMeal(meal)
            }
        }.collect { meals ->
            sendAlertsToCaregiver(meals)
        }
    }

    private suspend fun isMealOfMyPatient(meal: Meal): Boolean {
        return meal.patientUuid in caretakerAccountService.caretakerRepository.getByOwnerId(
            caretakerAccountService.authService.currentUserId
        ).patients
    }

    private suspend fun isThirdConsecutiveMissedMeal(meal: Meal): Boolean {
        return meal.missed && twoPreviousMealsAreMissed(meal) && meal.missedInLast15Minutes()
    }

    private suspend fun twoPreviousMealsAreMissed(meal: Meal): Boolean {
        val twoPreviousMeals = mealRepository.getPreviousMeals(meal, 2)
        return twoPreviousMeals.first().all { it.missed }
    }

    private suspend fun sendAlertsToCaregiver(meals: List<Meal>) {
        Log.d("test", meals.toString())
        for (meal in meals) {
            sendAlertToCaregiver(meal) // This is now allowed because we're in a coroutine context
        }
    }

    private suspend fun sendAlertToCaregiver(meal: Meal) {
        val patient = patientRepository.getById(meal.patientUuid)
        notificationService.showMissedMealsAlertNotification(patient!!)
    }
}

/*
Alert:
Ingrid Anderson has missed 3 consecutive meals!
 */
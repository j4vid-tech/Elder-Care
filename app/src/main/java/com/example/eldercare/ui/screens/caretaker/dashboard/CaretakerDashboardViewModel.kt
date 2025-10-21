package com.example.eldercare.ui.screens.caretaker.dashboard


import android.icu.util.Calendar
import com.example.eldercare.CARETAKER_PATIENT_LIST
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.model.repository.impl.CaretakerRepository
import com.example.eldercare.model.repository.impl.MealRepository
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.model.service.CaretakerAccountService
import com.example.eldercare.model.service.notifications.MissedMealsAlertService
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CaretakerDashboardViewModel @Inject constructor(
    private val patientRepository: PatientRepository,
    private val caretakerAccountService: CaretakerAccountService,
    private val caretakerRepository: CaretakerRepository,
    private val mealRepository: MealRepository,
    private val missedMealsAlertService: MissedMealsAlertService,
) : ElderCareViewModel() {
    val patients = patientRepository.getAll()
    val caretakers = caretakerRepository.getAll()
    val userId = caretakerAccountService.authService.currentUserId
    val meals = mealRepository.getAll()

    //TEST
    /*val userPatients = caretakerRepository.getPatientsForCaretaker(userId) { patients ->
        for (patientId in patients) {
            println("Patient ID: $patientId")
        }
    }*/

    fun onAllPatientsClick(appState: ElderCareAppState) {
        launchCatching {
            appState.navigate(CARETAKER_PATIENT_LIST)
        }
    }

    fun onDrawerClick(appState: ElderCareAppState, route: String) {
        launchCatching {
            appState.navigate(route)
        }

    }
    fun getStartOfToday(): Long {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DATE]
        calendar[year, month, day, 0, 0] = 0
        return calendar.timeInMillis
    }
    fun getEndOfDay(): Long {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DATE]
        calendar[year, month, day, 23, 59] = 59
        return calendar.timeInMillis
    }

    fun onNotifyClick(appState: ElderCareAppState) {
        launchCatching {
            missedMealsAlertService.alertCaregiversAboutConsecutivelyMissedMeals()
        }
    }

}






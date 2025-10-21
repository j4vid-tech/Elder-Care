package com.example.eldercare.ui.screens.patient.mealplan

import androidx.compose.runtime.mutableStateOf
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_PROFILE
import com.example.eldercare.model.Meal
import com.example.eldercare.model.datastore.PatientUserInfoStore
import com.example.eldercare.model.repository.impl.MealRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealplanViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val patientUserInfoStore: PatientUserInfoStore,
) : ElderCareViewModel() {
    var patientId = mutableStateOf("")
    var mealsOfConnectedPatient = mutableStateOf(listOf<Meal>())

    init {
        launchCatching {
            patientUserInfoStore.patientId().collect { value ->
                updatePatientIdValue(value)
                mealRepository.getAllByPatientIdToday(patientId.value).collect { meals ->
                    mealsOfConnectedPatient.value = meals
                }
            }
        }
    }

    private fun updatePatientIdValue(patientId: String) {
        this.patientId.value = patientId
    }

    fun onMealEatenChange(meal: Meal) {
        launchCatching { mealRepository.update(meal.id, meal.copy(gotEaten = !meal.gotEaten)) }
    }

    fun onShowQrCodeClick(appState: ElderCareAppState) {
        appState.navigate(PATIENT_PROFILE)
    }
}

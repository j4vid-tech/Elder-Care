package com.example.eldercare.ui.screens.caretaker.mealhistory


import androidx.lifecycle.SavedStateHandle
import com.example.eldercare.PATIENT_ID
import com.example.eldercare.common.ext.idFromParameter
import com.example.eldercare.model.Meal
import com.example.eldercare.model.repository.impl.MealRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MealHistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mealRepository: MealRepository,
) : ElderCareViewModel() {
    // Assuming you have a default value for patientId in the SavedStateHandle or you are certain it will always be there
    private val patientId: String = savedStateHandle[PATIENT_ID] ?: throw IllegalArgumentException("patientId must be provided")

    // MutableStateFlow to store the meals
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: Flow<List<Meal>> = _meals

    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        launchCatching {
            mealRepository.getAllMealPastByPatientId(patientId.idFromParameter()).collect { fetchedMeals ->
                _meals.value = fetchedMeals
            }
        }
    }
}
package com.example.eldercare.ui.screens.caretaker.CreateMeal

import android.icu.text.SimpleDateFormat
import android.icu.text.TimeZoneFormat.GMTOffsetPatternType
import android.icu.util.TimeZone
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.eldercare.CARETAKER_CREATE_MEAL
import com.example.eldercare.CARETAKER_DASHBOARD
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_ID
import com.example.eldercare.common.ext.idFromParameter
import com.example.eldercare.model.Meal
import com.example.eldercare.model.MealType
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.Repository
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject
import java.util.*



@HiltViewModel
class CreateMealViewModel @Inject constructor(
    private val mealRepository: Repository<Meal>,
    savedStateHandle: SavedStateHandle,
    private val patientRepository: PatientRepository,
) : ElderCareViewModel() {


    val patient = mutableStateOf(Patient())

    init {
        val patientId = savedStateHandle.get<String>(PATIENT_ID)
        if (patientId != null) {
            launchCatching {
                patient.value = patientRepository.getById(patientId.idFromParameter()) ?: Patient()
            }
        }
    }

    var uiState = mutableStateOf(CreateMealUiState(type = MealType.Breakfast, date = null))
        private set

    private val additionalInfo
        get() = uiState.value.additionalInfo

    private val patientUuid
        get() = patient.value.id

    private val type
        get() = uiState.value.type

    private val mealType
        get() = uiState.value.mealType

    private val dish
        get() = uiState.value.dish

    private val date
        get() = uiState.value.date

    private val dateString
        get() = uiState.value.dateString

    private val timeString
        get() = uiState.value.timeString


    fun onDropChange(newValue: MealType) {
        uiState.value = uiState.value.copy(type = newValue)
    }


    fun onDate1Change(newValue: String) {
        uiState.value = uiState.value.copy(dateString = newValue)

    }

    fun onTimeChange(newValue: String) {
        uiState.value = uiState.value.copy(timeString = newValue)

    }

    fun onDishChange(newValue: String) {
        uiState.value = uiState.value.copy(dish = newValue)
    }

    fun onAdditionalInfoChange(newValue: String) {
        uiState.value = uiState.value.copy(additionalInfo = newValue)
    }

    fun onAddMealClick(appState: ElderCareAppState, id: String) {

        launchCatching {

            val (day, month, year) = dateString.split('/').map { it.toInt() }
            val (hour, minute) = timeString.split(':').map { it.toInt() }
            val calendar = Calendar.getInstance(Locale("GMT+2"))
            // Set the date and time components
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month - 1) // Calendar months are 0-based
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            val timestamp = Timestamp(Date(calendar.timeInMillis))

            uiState.value = uiState.value.copy(date = timestamp)


            mealRepository.add(
                Meal(
                    patientUuid = id,
                    date = date,
                    type = type,
                    dish = dish,
                    gotEaten = false,
                    additionalInfo = additionalInfo,
                )
            )
            appState.navigateAndPopUp(CARETAKER_DASHBOARD, CARETAKER_CREATE_MEAL)
        }
    }

}
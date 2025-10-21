package com.example.eldercare.ui.screens.caretaker.patientprofile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.eldercare.CARETAKER_CREATE_MEAL
import com.example.eldercare.CARETAKER_MEAL_HISTORY
import com.example.eldercare.CARETAKER_PATIENT_PROFILE_Edit
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_ID
import com.example.eldercare.common.ext.idFromParameter
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val patientRepository: PatientRepository,
) : ElderCareViewModel() {
    fun onDetailClick(appState: ElderCareAppState, patientId: String) {

        appState.navigate("$CARETAKER_PATIENT_PROFILE_Edit?$PATIENT_ID={${patientId}}")
    }

    fun onMealHistoryClick(appState: ElderCareAppState, patientId: String) {
        appState.navigate("$CARETAKER_MEAL_HISTORY?$PATIENT_ID={${patientId}}")
    }

    fun onAddMealClick(appState: ElderCareAppState, patientId: String) {
        appState.navigate("$CARETAKER_CREATE_MEAL?$PATIENT_ID={${patientId}}")
    }

    val patient = mutableStateOf(Patient())
    init {
        val patientId = savedStateHandle.get<String>(PATIENT_ID)
        if (patientId != null) {
            launchCatching {
                patient.value = patientRepository.getById(patientId.idFromParameter()) ?: Patient()
            }
        }
    }
}
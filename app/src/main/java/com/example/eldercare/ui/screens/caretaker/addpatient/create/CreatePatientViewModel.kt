package com.example.eldercare.ui.screens.caretaker.addpatient.create

import androidx.compose.runtime.mutableStateOf
import com.example.eldercare.CARETAKER_CREATE_PATIENT
import com.example.eldercare.CARETAKER_PATIENT_LIST
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreatePatientViewModel @Inject constructor(
    private val patientRepository: PatientRepository,
) : ElderCareViewModel() {

    var uiState = mutableStateOf(CreatePatientUiState())
        private set

    private val fullName
        get() = uiState.value.fullName
    private val phoneNumber
        get() = uiState.value.phoneNumber
    private val dateOfBirth
        get() = uiState.value.dateOfBirth
    private val foodInterests
        get() = uiState.value.foodInterests
    private val dietaryRestrictions
        get() = uiState.value.dietaryRestrictions

    fun onFullNameChange(newValue: String) {
        uiState.value = uiState.value.copy(fullName = newValue)
    }

    fun onContactNumberChange(newValue: String) {
        uiState.value = uiState.value.copy(phoneNumber = newValue)
    }

    fun onDateOfBirthChange(newValue: String) {
        uiState.value = uiState.value.copy(dateOfBirth = newValue)
    }

    fun onPreferencesChange(newValue: String) {
        uiState.value = uiState.value.copy(foodInterests = newValue)
    }

    fun onDietaryRestrictionsChange(newValue: String) {
        uiState.value = uiState.value.copy(dietaryRestrictions = newValue)
    }

    fun onAddPatientClick(appState: ElderCareAppState) {
        launchCatching {
            patientRepository.add(
                Patient(
                    id = UUID.randomUUID().toString(),
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    dateOfBirth = dateOfBirth,
                    foodInterests = foodInterests,
                    dietaryRestrictions = dietaryRestrictions,
                )
            )
            appState.navigateAndPopUp(CARETAKER_PATIENT_LIST, CARETAKER_CREATE_PATIENT)
        }
    }

}

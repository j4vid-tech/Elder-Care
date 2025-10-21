package com.example.eldercare.ui.screens.patient.connect


import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_CONNECT_SCREEN
import com.example.eldercare.PATIENT_MEALPLAN_SCREEN
import com.example.eldercare.model.datastore.PatientUserInfoStore
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientConnectViewModel @Inject constructor(
    private val PatientUserInfoStore: PatientUserInfoStore
) : ElderCareViewModel() {
    fun storePatientId(appState: ElderCareAppState, patientId: String) {
        launchCatching {
            PatientUserInfoStore.savePatientId(patientId)
            appState.navigateAndPopUp(PATIENT_MEALPLAN_SCREEN, PATIENT_CONNECT_SCREEN)
        }
    }
}
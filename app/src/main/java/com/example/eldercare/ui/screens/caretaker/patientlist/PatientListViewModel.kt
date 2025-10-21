package com.example.eldercare.ui.screens.caretaker.patientlist

import com.example.eldercare.CARETAKER_PATIENT_PROFILE
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_ID
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val patientRepository: PatientRepository,
) : ElderCareViewModel() {
    val patients = patientRepository.getAll()

    fun onPatientClick(appState: ElderCareAppState, patientId: String) {
        launchCatching {
            appState.navigate("$CARETAKER_PATIENT_PROFILE?$PATIENT_ID={${patientId}}")
        }
    }
}

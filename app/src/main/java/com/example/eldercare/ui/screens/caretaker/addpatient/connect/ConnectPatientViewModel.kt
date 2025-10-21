package com.example.eldercare.ui.screens.caretaker.addpatient.connect

import com.example.eldercare.CARETAKER_CONNECT_PATIENT
import com.example.eldercare.CARETAKER_PATIENT_LIST
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.model.service.CaretakerAccountService
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectPatientViewModel @Inject constructor(
    private val caretakerAccountService: CaretakerAccountService,
) : ElderCareViewModel() {
    fun addPatientUuidToCaretaker(appState: ElderCareAppState, patientId: String) {
        launchCatching {
            caretakerAccountService.addPatient(patientId)
            appState.navigateAndPopUp(CARETAKER_PATIENT_LIST, CARETAKER_CONNECT_PATIENT)
        }
    }
}

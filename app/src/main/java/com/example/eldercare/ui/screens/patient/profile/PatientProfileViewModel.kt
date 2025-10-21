package com.example.eldercare.ui.screens.patient.profile

import androidx.compose.runtime.mutableStateOf
import com.example.eldercare.model.datastore.PatientUserInfoStore
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PatientProfileViewModel @Inject constructor(
    private val patientUserInfoStore: PatientUserInfoStore,
) : ElderCareViewModel() {

    var patientId = mutableStateOf("")

    init {
        launchCatching {
            patientUserInfoStore.patientId().collect { value ->
                updatePatientIdValue(value)
            }
        }
    }

    private fun updatePatientIdValue(patientId: String) {
        this.patientId.value = patientId
    }
}
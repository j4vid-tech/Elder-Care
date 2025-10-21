package com.example.eldercare.ui.screens.caretaker.patientprofileedit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_ID
import com.example.eldercare.common.ext.idFromParameter
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.impl.PatientRepository
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientProfileEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val patientRepository: PatientRepository,
) : ElderCareViewModel() {
    fun onEditProfileClick(appstate: ElderCareAppState) {

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

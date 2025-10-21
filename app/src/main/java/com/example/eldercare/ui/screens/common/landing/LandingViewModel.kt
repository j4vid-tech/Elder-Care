package com.example.eldercare.ui.screens.common.landing

import com.example.eldercare.CARETAKER_LOG_IN_SCREEN
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.PATIENT_CONNECT_SCREEN
import com.example.eldercare.PATIENT_MEALPLAN_SCREEN
import com.example.eldercare.model.datastore.PatientUserInfoStore
import com.example.eldercare.model.service.notifications.MealReminderService
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LandingViewModel @Inject constructor(
    private val patientUserInfoStore: PatientUserInfoStore,
    private val mealReminderService: MealReminderService,
    ) : ElderCareViewModel() {
    fun onChooseCaretakerClick(appState: ElderCareAppState) {
        launchCatching {
            appState.navigate(CARETAKER_LOG_IN_SCREEN)
        }
    }

    fun onChoosePatientClick(appState: ElderCareAppState) {
        launchCatching {
            patientUserInfoStore.patientId().collect { value -> navigateOnReceivePatientId(value, appState) }
        }
    }

    private fun navigateOnReceivePatientId(patientId: String, appState: ElderCareAppState) {
        if (patientId.isBlank()) {
            appState.navigate(PATIENT_CONNECT_SCREEN)
        } else {
            appState.navigate(PATIENT_MEALPLAN_SCREEN)
        }
    }

    fun onChooseNotifyTestClick(appState: ElderCareAppState) {
        launchCatching {
            mealReminderService.remindPatientAboutMealsThatAreDueToEat()
        }
    }
}

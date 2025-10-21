package com.example.eldercare.ui.screens.caretaker.signup

import androidx.compose.runtime.mutableStateOf
import com.example.eldercare.CARETAKER_LOG_IN_SCREEN
import com.example.eldercare.CARETAKER_SIGN_UP_SCREEN
import com.example.eldercare.CARETAKER_DASHBOARD
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.common.ext.isValidEmail
import com.example.eldercare.model.service.CaretakerAccountService
import com.example.eldercare.ui.screens.ElderCareViewModel
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val caretakerAccountService: CaretakerAccountService,
) : ElderCareViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val fullName
        get() = uiState.value.fullName
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password
    private val repeatPassword
        get() = uiState.value.repeatPassword

    fun onFullNameChange(newValue: String) {
        uiState.value = uiState.value.copy(fullName = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(appState: ElderCareAppState) {
        if (fullName.isBlank()) {
            // SnackbarManager.showMessage(AppText.full_name_error)
            return
        }

        if (!email.isValidEmail()) {
            // SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            // SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        if (password != repeatPassword) {
            // SnackbarManager.showMessage(AppText.password_mismatch_error)
            return
        }

        launchCatching {
            try {
                caretakerAccountService.signUp(fullName, email, password)
            } catch (e: FirebaseAuthUserCollisionException) {
                // SnackbarManager.showMessage(AppText.email_address_already_in_use)
                return@launchCatching
            }
            appState.navigateAndPopUp(CARETAKER_DASHBOARD, CARETAKER_SIGN_UP_SCREEN)
        }
    }

    fun onAlreadyHaveAccountClick(appState: ElderCareAppState) {
        launchCatching {
            appState.navigate(CARETAKER_LOG_IN_SCREEN)
        }
    }
}

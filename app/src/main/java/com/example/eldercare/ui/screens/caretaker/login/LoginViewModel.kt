package com.example.eldercare.ui.screens.caretaker.login

import androidx.compose.runtime.mutableStateOf
import com.example.eldercare.CARETAKER_LOG_IN_SCREEN
import com.example.eldercare.CARETAKER_SIGN_UP_SCREEN
import com.example.eldercare.CARETAKER_DASHBOARD
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.common.ext.isValidEmail
import com.example.eldercare.model.service.CaretakerAccountService
import com.example.eldercare.ui.screens.ElderCareViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val caretakerAccountService: CaretakerAccountService,
) : ElderCareViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(appState: ElderCareAppState) {
        if (!email.isValidEmail()) {
            // SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            // SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            caretakerAccountService.authService.authenticate(email, password)
            appState.navigateAndPopUp(CARETAKER_DASHBOARD, CARETAKER_LOG_IN_SCREEN)
        }
    }

    fun onNoAccountClick(appState: ElderCareAppState) {
        launchCatching {
            appState.navigate(CARETAKER_SIGN_UP_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            // SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            caretakerAccountService.authService.sendRecoveryEmail(email)
            // SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }
}

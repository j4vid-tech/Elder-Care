package com.example.eldercare

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eldercare.model.service.notifications.notificationPermission
import com.example.eldercare.ui.screens.caretaker.CreateMeal.CreateMealScreen
import com.example.eldercare.ui.screens.caretaker.addpatient.connect.ConnectPatientScreen
import com.example.eldercare.ui.screens.caretaker.addpatient.create.CreatePatientScreen
import com.example.eldercare.ui.screens.caretaker.dashboard.CaretakerDashboardScreen
import com.example.eldercare.ui.screens.caretaker.login.LoginScreen
import com.example.eldercare.ui.screens.caretaker.mealhistory.MealHistoryScreen
import com.example.eldercare.ui.screens.caretaker.patientlist.PatientListScreen
import com.example.eldercare.ui.screens.caretaker.patientprofile.PatientProfileScreen
import com.example.eldercare.ui.screens.caretaker.patientprofileedit.PatientProfileEditScreen
import com.example.eldercare.ui.screens.caretaker.signup.SignUpScreen
import com.example.eldercare.ui.screens.common.landing.LandingScreen
import com.example.eldercare.ui.screens.patient.connect.PatientConnectScreen
import com.example.eldercare.ui.screens.patient.mealplan.MealplanScreen
import com.example.eldercare.ui.screens.patient.profile.ProfileScreen
import com.example.eldercare.ui.theme.ElderCareTheme

/**
 * Root-level composable.
 */
@Composable
fun ElderCareApp() {
    ElderCareTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val appState = rememberAppState()
            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = LANDING_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    elderCareGraph(appState)
                }
            }

        }
    }
    notificationPermission()
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        ElderCareAppState(navController)
    }

fun NavGraphBuilder.elderCareGraph(appState: ElderCareAppState) {
    composable(LANDING_SCREEN) {
        LandingScreen(appState = appState)
    }

    composable(CARETAKER_LOG_IN_SCREEN) {
        LoginScreen(appState = appState)
    }

    composable(CARETAKER_SIGN_UP_SCREEN) {
        SignUpScreen(appState = appState)
    }

    composable(PATIENT_MEALPLAN_SCREEN) {
        MealplanScreen(appState = appState)
    }

    composable(PATIENT_CONNECT_SCREEN) {
        PatientConnectScreen(appState = appState)
    }

    composable(CARETAKER_DASHBOARD) {
        CaretakerDashboardScreen(appState = appState)
    }

    composable(CARETAKER_PATIENT_LIST) {
        PatientListScreen(appState = appState)
    }
    composable(CARETAKER_PATIENT_PROFILE) {
        PatientProfileScreen(appState = appState)
    }
    composable(CARETAKER_PATIENT_PROFILE_Edit) {
        PatientProfileEditScreen(appState = appState)
    }
    composable(CARETAKER_CREATE_PATIENT) {
        CreatePatientScreen(appState = appState)
    }

    composable(CARETAKER_CREATE_MEAL) {
        CreateMealScreen(appState = appState)
    }

    composable(CARETAKER_MEAL_HISTORY) {
        MealHistoryScreen(appState = appState)
    }

    composable(CARETAKER_CONNECT_PATIENT) {
        ConnectPatientScreen(appState = appState)
    }
    composable(CARETAKER_CONNECT_PATIENT) {
        ConnectPatientScreen(appState = appState)
    }
    composable(
        route = "$CARETAKER_PATIENT_PROFILE$PATIENT_ID_ARG",
        arguments = listOf(navArgument(PATIENT_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        PatientProfileScreen(appState = appState)
    }
    composable(
        route = "$CARETAKER_PATIENT_PROFILE_Edit$PATIENT_ID_ARG",
        arguments = listOf(navArgument(PATIENT_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        PatientProfileEditScreen(appState = appState)
    }

    composable(
        route = "$CARETAKER_CREATE_MEAL$PATIENT_ID_ARG",
        arguments = listOf(navArgument(PATIENT_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        CreateMealScreen(appState = appState)
    }

    composable(
        route = "$CARETAKER_MEAL_HISTORY$PATIENT_ID_ARG",
        arguments = listOf(navArgument(PATIENT_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        MealHistoryScreen(appState = appState)
    }

    composable(PATIENT_PROFILE) {
        ProfileScreen(appState = appState)
    }
}
package com.example.eldercare.ui.screens.patient.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.common.composable.QrCodeGenerator

@Composable
fun ProfileScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: PatientProfileViewModel = hiltViewModel()
) {
    val patientId by viewModel.patientId
    Box(
        contentAlignment = Alignment.Center, // Centers content inside the Box
        modifier = Modifier.fillMaxSize()    // Makes the Box fill the entire available space
    ) {
        QrCodeGenerator(
            textToEncode = patientId,
            contentDescription = patientId,
        )
    }
}
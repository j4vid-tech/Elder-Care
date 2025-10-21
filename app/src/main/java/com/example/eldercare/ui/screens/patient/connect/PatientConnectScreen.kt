package com.example.eldercare.ui.screens.patient.connect


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.QRScan


@Composable
fun PatientConnectScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: PatientConnectViewModel = hiltViewModel()
) {
    val func: (String) -> Unit = {uuid -> viewModel.storePatientId(appState, uuid)}
    QRScan(onQrCodeScanned = func)
}

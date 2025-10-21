package com.example.eldercare.ui.screens.caretaker.addpatient.connect

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.QRScan

@Composable
fun ConnectPatientScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: ConnectPatientViewModel = hiltViewModel()
) {
    val func: (String) -> Unit = { uuid -> viewModel.addPatientUuidToCaretaker(appState, uuid) }
    QRScan(onQrCodeScanned = func)
}
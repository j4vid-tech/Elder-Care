package com.example.eldercare.ui.screens.caretaker.addpatient.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.MyTextField

@Composable
fun CreatePatientScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: CreatePatientViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Headline("")

            MyTextField(
                value = uiState.fullName,
                labelValue = stringResource(id = R.string.full_name),
                onNewValue = viewModel::onFullNameChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextField(
                value = uiState.phoneNumber,
                labelValue = stringResource(id = R.string.contactNumber),
                onNewValue = viewModel::onContactNumberChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextField(
                value = uiState.dateOfBirth,
                labelValue = stringResource(id = R.string.dateOfBirth),
                onNewValue = viewModel::onDateOfBirthChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextField(
                value = uiState.foodInterests,
                labelValue = stringResource(id = R.string.preferences),
                onNewValue = viewModel::onPreferencesChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextField(
                value = uiState.dietaryRestrictions,
                labelValue = stringResource(id = R.string.dietaryRestrictions),
                onNewValue = viewModel::onDietaryRestrictionsChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(15.dp))
            ButtonComponent(value = stringResource(id = R.string.add_patient)) {
                viewModel.onAddPatientClick(appState)
            }
        }
    }
}

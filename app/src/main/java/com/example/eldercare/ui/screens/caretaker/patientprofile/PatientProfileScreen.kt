package com.example.eldercare.ui.screens.caretaker.patientprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.QrCodeGenerator
import com.example.eldercare.ui.theme.spacing


@Composable
fun PatientProfileScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: PatientProfileViewModel = hiltViewModel()
) {
    val patient by viewModel.patient
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        //.padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Headline("")

            PersonalInformationOverview(appState,modifier,viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.detail)) {
                viewModel.onDetailClick(appState, patient.id)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(id = R.string.personal_qr_string),
                textAlign = TextAlign.Center)

            QrCodeGenerator(
                textToEncode = patient.id,
                contentDescription = patient.id,
            )
            Spacer(modifier = Modifier.height(45.dp))
            ButtonComponent(value = stringResource(id = R.string.meal_history)) {
                viewModel.onMealHistoryClick(
                    appState, patient.id
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.add_meal)) {
                viewModel.onAddMealClick(appState, patient.id)
            }

        }
    }
}

@Composable
fun PersonalInformationOverview(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: PatientProfileViewModel = hiltViewModel())
{
    val patient by viewModel.patient

    Box(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.medium)
            .border(1.dp, MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .height(200.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
            /* .padding(
                 horizontal = MaterialTheme.spacing.medium,
                 vertical = MaterialTheme.spacing.medium
             )*/,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = patient.fullName, fontSize = 8.em)

                Text(
                    text = stringResource(id =R.string.dietaryRestrictions ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.dietaryRestrictions)

                Text(
                    text = stringResource(id =R.string.phone_number ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.phoneNumber)


            }
        }
    }
}


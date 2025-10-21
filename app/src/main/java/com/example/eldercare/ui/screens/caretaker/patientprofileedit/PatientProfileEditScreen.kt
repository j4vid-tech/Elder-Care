package com.example.eldercare.ui.screens.caretaker.patientprofileedit

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.ui.theme.spacing


@Composable
fun PatientProfileEditScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: PatientProfileEditViewModel = hiltViewModel())
{
    val patient by viewModel.patient

    Box(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(top = MaterialTheme.spacing.medium)
            .border(1.dp, MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .height(725.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Text(text = patient.fullName, fontSize = 8.em)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id =R.string.phone_number ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.phoneNumber)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id =R.string.dateOfBirth ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.dateOfBirth)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id =R.string.preferences ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.foodInterests)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id =R.string.dietaryRestrictions ),
                    modifier = Modifier.padding(MaterialTheme.spacing.extrasmall),
                    fontSize = 20.sp,
                )
                Text(text = patient.dietaryRestrictions)
                Spacer(modifier = Modifier.height(35.dp))
                ButtonComponent(value = stringResource(id = R.string.edit_profile)) {
                    viewModel.onEditProfileClick(appState)
                }



            }
        }
    }
}


package com.example.eldercare.ui.screens.common.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.Greeting
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.Logo

@Composable
fun LandingScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: LandingViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Headline("")
            Spacer(modifier = Modifier.height(100.dp))
            Logo(logoSize = 250, painterResource(id = R.drawable.eldercare))
            Spacer(modifier = Modifier.height(75.dp))
            Greeting(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(100.dp))
            ButtonComponent(value = stringResource(id = R.string.elderly)) {
                viewModel.onChoosePatientClick(
                    appState
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.caregiver)) {
                viewModel.onChooseCaretakerClick(
                    appState
                )
            }
            ButtonComponent(value = "Elderly Notification Test") {
                viewModel.onChooseNotifyTestClick(
                    appState
                )
            }
        }
    }
}

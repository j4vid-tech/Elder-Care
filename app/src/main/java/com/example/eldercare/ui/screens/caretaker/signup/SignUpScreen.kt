package com.example.eldercare.ui.screens.caretaker.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.EmailField
import com.example.eldercare.common.composable.Greeting
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.Logo
import com.example.eldercare.common.composable.MyTextField
import com.example.eldercare.common.composable.PasswordField
import com.example.eldercare.common.composable.RepeatPasswordField

@Composable
fun SignUpScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
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
        )
        {
            Headline("")
            Spacer(modifier = Modifier.height(100.dp))
            Logo(logoSize = 100, painterResource(id = R.drawable.eldercare))
            Spacer(modifier = Modifier.height(20.dp))
            Greeting(value = stringResource(id = R.string.sign_up))
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(
                value = uiState.fullName,
                labelValue = stringResource(id = R.string.full_name),
                onNewValue = viewModel::onFullNameChange,
                painterResource = painterResource(id = R.drawable.profile)
            )
            Spacer(modifier = Modifier.height(10.dp))
            EmailField(
                value = uiState.email,
                onNewValue = viewModel::onEmailChange,
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                value = uiState.password,
                onNewValue = viewModel::onPasswordChange,
            )
            Spacer(modifier = Modifier.height(10.dp))
            RepeatPasswordField(
                value = uiState.repeatPassword,
                onNewValue = viewModel::onRepeatPasswordChange,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(id = R.string.password_min_length_info))
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.sign_up)) {
                viewModel.onSignUpClick(appState)
            }
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.already_have_account)) {
                viewModel.onAlreadyHaveAccountClick(appState)
            }
        }
    }
}

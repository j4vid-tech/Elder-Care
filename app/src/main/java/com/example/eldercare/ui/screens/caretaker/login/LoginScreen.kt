package com.example.eldercare.ui.screens.caretaker.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.EmailField
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.Logo
import com.example.eldercare.common.composable.PasswordField
import com.example.eldercare.R.string as AppText


@Composable
fun LoginScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Surface(
        color = colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
        //.padding(20.dp)
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
            EmailField(value = uiState.email, onNewValue = viewModel::onEmailChange)
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(value = uiState.password, onNewValue = viewModel::onPasswordChange)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.log_in)) {
                viewModel.onSignInClick(
                    appState
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ClickableText(
                text = AnnotatedString(stringResource(id = AppText.forgot_password)),
                onClick = { viewModel.onForgotPasswordClick() },
                style = TextStyle(
                    color =colorScheme.secondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Default
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.no_account)) {
                viewModel.onNoAccountClick(
                    appState
                )
            }
        }
    }
}

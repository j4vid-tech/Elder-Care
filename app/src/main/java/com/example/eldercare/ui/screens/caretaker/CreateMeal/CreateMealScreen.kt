package com.example.eldercare.ui.screens.caretaker.CreateMeal

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.ButtonComponent
import com.example.eldercare.common.composable.DropDownMenuDemo
import com.example.eldercare.common.composable.MyCalander
import com.example.eldercare.common.composable.MyTextField
import com.example.eldercare.common.composable.MyTime
import java.util.Date
import com.example.eldercare.common.composable.Headline
import com.example.eldercare.common.composable.MyCalander

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateMealScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: CreateMealViewModel = hiltViewModel()
) {
    val patient by viewModel.patient
    val uiState by viewModel.uiState

    val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z")
    val currentDate = sdf.format(Date())

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Headline("")

            DropDownMenuDemo(onNewValue = viewModel::onDropChange)
            
            MyCalander(
                onNewValue = viewModel::onDate1Change,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            MyTime(
                onNewValue = viewModel::onTimeChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )


            MyTextField(
                value = uiState.dish,
                labelValue = stringResource(id = R.string.dish),
                onNewValue = viewModel::onDishChange,
                painterResource = painterResource(id = R.drawable.add),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

            )

            MyTextField(

                value = uiState.additionalInfo,
                labelValue = stringResource(id = R.string.additional_Info),
                onNewValue = viewModel::onAdditionalInfoChange,
                painterResource = painterResource(id = R.drawable.add),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

            )
            


            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(15.dp))
            ButtonComponent(value = stringResource(id = R.string.add_Meal)) {
                viewModel.onAddMealClick(appState, patient.id)
            }
        }
    }
}
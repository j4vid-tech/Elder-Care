package com.example.eldercare.common.composable

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text


import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldercare.R
import java.util.*



// Creating a composable function
// to create a Time Picker
// Calling this function as content
// in the above function
@Composable
fun MyTime(
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
){

    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
            onNewValue(mTime.value)
        }, mHour, mMinute, true
    )

    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // On button click, TimePicker is
        // displayed, user can select a time
        Button(onClick = { mTimePickerDialog.show() }, colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.primary)) {
            Text(text = stringResource(id = R.string.open_time_picker), color = Color.White)
        }

        // Add a spacer of 100dp
        Spacer(modifier = Modifier)

        // Display selected time
        Text(text = stringResource(id = R.string.selected_time)+": ${mTime.value}", fontSize = 30.sp)
    }
}



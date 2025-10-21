package com.example.eldercare.common.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eldercare.model.Meal
import com.example.eldercare.model.MealType

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.res.stringResource
import com.example.eldercare.R


@Composable
fun DropDownMenuDemo(onNewValue: (MealType) -> Unit, modifier: Modifier = Modifier) {


    var selectedMeal by remember { mutableStateOf(MealType.Breakfast) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(stringResource(id = R.string.selected_meal)+": $selectedMeal")
        Spacer(modifier = Modifier.height(16.dp))



        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selectedMeal.name,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                modifier = Modifier
                    .padding(16.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                MealType.values().forEach { meal ->
                    DropdownMenuItem(
                        text = { Text(text = meal.name)},
                        onClick = {
                            selectedMeal = meal
                            expanded = false
                            onNewValue(meal) // Notify the caller of the selected meal
                        }

                    )


                }
            }
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.select_a_meal))
            }
        }
    }
}






package com.example.eldercare.ui.screens.patient.mealplan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.ext.getHoursAndMinutes
import com.example.eldercare.model.Meal
import com.example.eldercare.model.exampleBreakfastMeal

@Composable
fun MealplanScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: MealplanViewModel = hiltViewModel()
) {
    val meals = viewModel.mealsOfConnectedPatient

    Scaffold(
        modifier = modifier,
        bottomBar = { // bottomBar is always placed at the bottom of the screen
            Button(
                onClick = { viewModel.onShowQrCodeClick(appState) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(stringResource(id = R.string.my_qr_show))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding) // Consider padding so content doesn't overlap with the bottomBar
                .padding(5.dp)
        ) {
            MealCardList(
                meals, onMealEatenChange = viewModel::onMealEatenChange
            )
        }
    }
}

@Composable
fun MealCardList(
    mealList: State<List<Meal>>,
    modifier: Modifier = Modifier,
    onMealEatenChange: (Meal) -> Unit
) {
    LazyColumn {
        items(mealList.value) { meal ->
            MealCard(meal, onCheckChange = { onMealEatenChange(meal) })
        }
    }
}

@Composable
fun MealCard(
    meal: Meal,
    modifier: Modifier = Modifier,
    onCheckChange: () -> Unit?,
) {
    ElevatedCard(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
        ) {
            Row {
                Checkbox(
                    checked = meal.gotEaten,
                    enabled = !meal.missed,
                    onCheckedChange = { onCheckChange() },
                    modifier = Modifier
                        .scale(2.5f)
                        .padding(horizontal = 20.dp)
                        .align(Alignment.CenterVertically),
                )
                Column(
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    Text(
                        text = meal.type.name + " " + stringResource(id = R.string.at) + " " + (meal.date?.getHoursAndMinutes()
                            ?: "???") + ":",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = meal.dish,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            if (meal.additionalInfo.isNotBlank()) {
                Text(
                    text = meal.additionalInfo,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview
@Composable
fun MealCardPreview() {
    MealCard(
        meal = exampleBreakfastMeal,
        onCheckChange = { },
    )
}
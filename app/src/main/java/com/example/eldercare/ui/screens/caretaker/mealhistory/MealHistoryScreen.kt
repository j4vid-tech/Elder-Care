package com.example.eldercare.ui.screens.caretaker.mealhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.common.ext.getDateAndTime
import com.example.eldercare.model.Meal


@Composable
fun MealHistoryScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: MealHistoryViewModel = hiltViewModel()
) {
    val meals = viewModel.meals.collectAsStateWithLifecycle(emptyList())
    MealHistoryList(meals = meals)
}

@Composable
fun MealHistoryList(meals: State<List<Meal>>) {
    LazyColumn {
        items(meals.value) { meal -> MealHistoryListItem(meal) }
    }
}




@Composable
fun MealHistoryListItem(
    meal: Meal,
    modifier: Modifier = Modifier
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
                Text(
                    text = meal.date?.getDateAndTime() ?: "???",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = meal.type.toString(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Column(
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Text(
                    text = meal.dish,
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (meal.gotEaten) Color.Black else Color.Red,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
   

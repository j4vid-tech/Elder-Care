package com.example.eldercare.ui.screens.caretaker.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eldercare.CARETAKER_CONNECT_PATIENT
import com.example.eldercare.CARETAKER_CREATE_PATIENT
import com.example.eldercare.CARETAKER_PATIENT_LIST
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.R
import com.example.eldercare.common.composable.Logo
import com.example.eldercare.model.Caretaker
import com.example.eldercare.model.Meal
import com.example.eldercare.model.Patient
import com.example.eldercare.ui.theme.spacing
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch
import java.util.Date


@Composable
fun CaretakerDashboardScreen(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: CaretakerDashboardViewModel = hiltViewModel(),

    ) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        DrawerItem(
            icon = Icons.Default.Home,
            label = stringResource(id = R.string.overview),
            secondaryLabel = "",
            route = ""
        ),

        DrawerItem(
            icon = Icons.Default.AddCircle,
            label = stringResource(id = R.string.add_patient),
            secondaryLabel = "",
            route = CARETAKER_CREATE_PATIENT

        ),
        DrawerItem(
            icon = Icons.Default.Search,
            label = stringResource(id = R.string.connect_patient),
            secondaryLabel = "",
            route = CARETAKER_CONNECT_PATIENT
        ),
        DrawerItem(
            icon = Icons.Default.Face,
            label = stringResource(id = R.string.patient_list),
            secondaryLabel = "",
            route = CARETAKER_PATIENT_LIST
        ),
    )

    var selectedItem by remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extralarge),
                    contentAlignment = Alignment.Center,
                ) {
                    Logo(logoSize = 100, painterResource(id = R.drawable.eldercare))
                }

                items.forEach { item ->


                    NavigationDrawerItem(
                        label = { Text(text = item.label) },
                        selected = item == selectedItem,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
                            viewModel.onDrawerClick(appState, selectedItem.route)
                        },
                        icon = {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                            )

                        },
                        badge = { Text(text = item.secondaryLabel) },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                }
            }
        },
        content = {

            Content2(
                onMenuIconClick = { scope.launch { drawerState.open() } },
                appState,
                modifier,
                viewModel
            )
        }
    )
}

data class DrawerItem(
    val icon: ImageVector,
    val label: String,
    val secondaryLabel: String,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content2(
    onMenuIconClick: () -> Unit,
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: CaretakerDashboardViewModel = hiltViewModel(),
) {
    val patients = viewModel.patients.collectAsStateWithLifecycle(emptyList())
    val caretakers = viewModel.caretakers.collectAsStateWithLifecycle(emptyList())
    val meals = viewModel.meals.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onMenuIconClick) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer")
                    }
                },
                title = { Text(text = stringResource(id = R.string.overview)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                //.fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(MaterialTheme.spacing.medium)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            FirstGreeting(stringResource(id = R.string.greeting), caretakers)
            Spacer(modifier = Modifier.height(4.dp))
            overview(meals = meals, viewModel = viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.alerts),
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(4.dp))
            ScrollBox(patients, meals)
            ButtonSection(appState, modifier, viewModel)
        }
    }
}


@Composable
fun ButtonSection(
    appState: ElderCareAppState,
    modifier: Modifier = Modifier,
    viewModel: CaretakerDashboardViewModel = hiltViewModel()

) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .padding(horizontal = 10.dp)
            .height(50.dp), onClick = {
            viewModel.onAllPatientsClick(
                appState
            )
        }) {
        Text(text = stringResource(id = R.string.patient_list))
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .padding(horizontal = 10.dp)
            .height(50.dp), onClick = {
            viewModel.onNotifyClick(
                appState
            )
        }) {
        Text(text = "Test Notifications")
    }
}

@Composable
fun overview(
    viewModel: CaretakerDashboardViewModel,
    meals: State<List<Meal>>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
        //verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .size(110.dp, 100.dp),
            Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.remaining_meals)) //GÖR OM TILL STRÄNG
                countRemaining(meals)
            }
        }

        Box(
            modifier = Modifier
                //.border(2.dp, MaterialTheme.colorScheme.primary)
                .background(MaterialTheme.colorScheme.tertiary)
                .size(100.dp, 100.dp),
            Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.missed_meals),
                    color = MaterialTheme.colorScheme.secondary,
                )
                countMissed(meals, viewModel)
            }
        }

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .size(100.dp, 100.dp),
            Alignment.Center

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.eaten_meals))
                countEaten(meals)
            }
        }

    }
}

@Composable
fun countEaten(
    meals: State<List<Meal>>,
    viewModel: CaretakerDashboardViewModel = hiltViewModel()
) {
    val dayStart = Timestamp(Date(viewModel.getStartOfToday()))
    val dayEnd = Timestamp(Date(viewModel.getEndOfDay()))
    val eatenMeals =
        meals.value.filter { it.gotEaten && it.date!! >= dayStart && it.date!! < dayEnd }
    val numberOfEatenMeals = eatenMeals.size
    val userId = viewModel.userId
    Text("$numberOfEatenMeals")

}

/**
 * list of patients belonging to the caregiver
 * create a list with getallmealstodaybypatientid
 * count all meals that are eaten
 */
@Composable
fun countMissed(
    meals: State<List<Meal>>,
    viewModel: CaretakerDashboardViewModel,
) {
    val dayStart = Timestamp(Date(viewModel.getStartOfToday()))
    val dayEnd = Timestamp(Date(viewModel.getEndOfDay()))
    val filteredMeals = meals.value.filter { it.date != null }
    val missedMeals =
        filteredMeals.filter {
            it.missed
                    && it.date!! >= dayStart
                    && it.date!! < dayEnd
        }
    val numberOfMissedMeals = missedMeals.size
    Text("$numberOfMissedMeals", color = MaterialTheme.colorScheme.secondary,)

}

@Composable
fun countRemaining(
    meals: State<List<Meal>>,
    viewModel: CaretakerDashboardViewModel = hiltViewModel()
) {
    val dayStart = Timestamp(Date(viewModel.getStartOfToday()))
    val dayEnd = Timestamp(Date(viewModel.getEndOfDay()))
    val remainingMeals =
        meals.value.filter { !it.gotEaten && !it.missed && it.date!! >= dayStart && it.date!! < dayEnd }
    val numberOfRemainingMeals = remainingMeals.size
    Text("$numberOfRemainingMeals")

}

@Composable
fun FirstGreeting(
    value: String,
    caretakers: State<List<Caretaker>>,
    viewModel: CaretakerDashboardViewModel = hiltViewModel(),
) {
    Column {
        Text(
            text = value,
            textAlign = TextAlign.Left
        )
        val userId = viewModel.userId
        val caretakerWithUserId = caretakers.value.find { it.ownerId == userId }
        if (caretakerWithUserId != null) {
            Text(text = caretakerWithUserId.fullName)
        }
    }
}


@Composable
fun ListItem(
    patient: Patient,
) {
    Text(text = patient.fullName)
}

@Composable
fun ScrollBox(
    patients: State<List<Patient>>,
    meals: State<List<Meal>>,
    viewModel: CaretakerDashboardViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 25.dp)
            //.border(2.dp, MaterialTheme.colorScheme.primary)
            .background(MaterialTheme.colorScheme.background)
            .size(200.dp, 390.dp)
            .padding(5.dp),
        Alignment.TopCenter
    ) {
        val dayStart = Timestamp(Date(viewModel.getStartOfToday()))
        val dayEnd = Timestamp(Date(viewModel.getEndOfDay()))

        val filteredPatients = patients.value.filter { patient ->
            meals.value.any { meal ->
                meal.patientUuid == patient.id && meal.missed && meal.date!! >= dayStart && meal.date!! < dayEnd
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp, 50.dp)
                .background(MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            items(filteredPatients) { patient ->
                ListItem(patient = patient)
            }
        }
    }

}


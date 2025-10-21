package com.example.eldercare.ui.screens.caretaker.patientlist

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eldercare.CARETAKER_CREATE_PATIENT
import com.example.eldercare.ElderCareAppState
import com.example.eldercare.model.Patient
import com.example.eldercare.ui.theme.spacing
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.eldercare.CARETAKER_CONNECT_PATIENT
import com.example.eldercare.R
import com.example.eldercare.common.composable.Headline

enum class MultiFloatingState{
    Expanded,
    Collapsed
}

class MinFabItem(
    val icon:ImageVector,
    val label:String,
    val identifier:Identifier,
)

enum class Identifier{
    ANPFab,
    AEPFab
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun PatientListScreen(
    appState: ElderCareAppState,
    viewModel: PatientListViewModel = hiltViewModel()
){
    val patients= viewModel.patients.collectAsStateWithLifecycle(emptyList())
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }
    val items = listOf(
        MinFabItem(
            icon = Icons.Default.AddCircle,
            label = stringResource(id = R.string.add_patient),
            identifier = Identifier.ANPFab
        ),
        MinFabItem(
            icon = Icons.Default.AddCircle,
            label = stringResource(id = R.string.add_existing_patient),
            identifier = Identifier.AEPFab
        )
    )
        Scaffold(floatingActionButton = {

            //moved Multi-floatButton to ButtonComposable
            MultiFloatButton(
                multiFloatingState = multiFloatingState,
                onMultiFabStateChange = {
                    multiFloatingState = it
                },
                items = items,
                appState = appState)
        }) {
            Headline(value = "")
            Spacer(modifier = Modifier.height(50.dp))
            if (patients.value.isEmpty()) {
                Text(stringResource(id = R.string.no_patients_found))
            } else {
                PatientList(appState, viewModel, patients)
            }
            Spacer(modifier = Modifier.height(150.dp))

        }
}

@Composable
fun MinFab(
    appState: ElderCareAppState,
    item: MinFabItem,
    onMinFabItemClick:(MinFabItem) -> Unit,
    ){
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(180.dp)
                .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        onMinFabItemClick.invoke(item)
                    },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 20.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.label,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(MaterialTheme.spacing.small),
                    textAlign = TextAlign.Left
                )

                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 150.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }

        }
}

@Composable
fun MultiFloatButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange: (MultiFloatingState) -> Unit,
    items:List<MinFabItem>,
    appState: ElderCareAppState,
){
    val transition = updateTransition(
        targetState = multiFloatingState,
        label = "transition")

    val rotate by transition.animateFloat (label = "rotate"){
        if(it == MultiFloatingState.Expanded) 315f else 0f
    }

    Column (
        horizontalAlignment = Alignment.End
    ){
        if(transition.currentState == MultiFloatingState.Expanded){
            items.forEach{
                MinFab(
                    item = it,
                    onMinFabItemClick = {minFabItem ->
                        when(minFabItem.identifier){

                            Identifier.AEPFab -> {
                                appState.navigate(CARETAKER_CONNECT_PATIENT)
                            }

                            Identifier.ANPFab -> {
                                appState.navigate(CARETAKER_CREATE_PATIENT)
                            }

                        }

                    },
                    appState = appState)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        FloatingActionButton(
            onClick = {
                onMultiFabStateChange(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.primary

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.rotate(rotate)
            )


        }
    }
}


@Composable
fun PatientList(
    appState: ElderCareAppState,
    viewModel: PatientListViewModel,
    patients: State<List<Patient>>
) {
    Column {
        Headline("")

        LazyColumn {
            items(patients.value) { patient -> PatientListItem(appState, viewModel, patient) }
        }
    }
}

@Composable
fun PatientListItem(
    appState: ElderCareAppState,
    viewModel: PatientListViewModel,
    patient: Patient,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .background(Color.White)
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
            .clickable(onClick = { viewModel.onPatientClick(appState, patient.id) })
    )
    {
        Text(
            text = patient.fullName,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
        )
    }
}



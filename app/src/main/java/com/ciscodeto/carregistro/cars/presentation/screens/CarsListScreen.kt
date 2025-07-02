package com.ciscodeto.carregistro.cars.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ciscodeto.carregistro.cars.presentation.screens.composables.CarListElement
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import com.ciscodeto.carregistro.core.presentation.composables.AppTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CarsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarsListViewModel = koinViewModel(),
) {
    val cars by viewModel.cars.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        topBar = { AppTopBar { viewModel.syncCars() } }
    ) { innerPadding ->
        LazyColumn(modifier = modifier
            .padding(innerPadding)
        ) {
            items(cars) { car ->
                CarListElement(carUi = car)
            }
        }
    }
}
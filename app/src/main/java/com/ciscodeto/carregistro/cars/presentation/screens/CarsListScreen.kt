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
import com.ciscodeto.carregistro.cars.presentation.screens.composables.CarListElement
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CarsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarsListViewModel = koinViewModel(),
) {
    val cars by viewModel.cars.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text("CarRegistro") }
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
package com.ciscodeto.carregistro.cars.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CarsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarsListViewModel = koinViewModel(),
) {
    val cars = viewModel.cars

    LazyColumn(modifier = modifier) {

    }
}
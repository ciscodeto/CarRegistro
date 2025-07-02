package com.ciscodeto.carregistro.cars.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ciscodeto.carregistro.cars.presentation.screens.composables.CarListElement
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import com.ciscodeto.carregistro.core.presentation.composables.AppTopBar
import com.ciscodeto.carregistro.core.presentation.composables.DropdownMenuInfo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CarsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarsListViewModel = koinViewModel(),
) {
    val cars by viewModel.cars.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppTopBar {
                listOf(
                    DropdownMenuInfo(
                        text = "Sincronizar com externo",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Sincronizar",
                                tint = Color(0xFFD6BFA1)
                            )
                        },
                        onClick = { viewModel.syncCars() }
                    ),
                    DropdownMenuInfo(
                        text = "Adicionar Veículo",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Adicionar Veículo",
                                tint = Color(0xFFD6BFA1)
                            )
                        },
                        onClick = { viewModel.createCar() }
                    )
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .padding(paddingValues)
        ) {
            items(cars) { car ->
                CarListElement(
                    carUi = car,
                    onDeleteClick = { viewModel.deleteCar(car.id) },
                    onEditClick = { viewModel.updateCar(car) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}
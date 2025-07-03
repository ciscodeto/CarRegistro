package com.ciscodeto.carregistro.cars.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ciscodeto.carregistro.cars.presentation.screens.composables.card.CarListElement
import com.ciscodeto.carregistro.cars.presentation.screens.composables.dialog.ConfirmSelectionDialog
import com.ciscodeto.carregistro.cars.presentation.screens.composables.dialog.FormDialog
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import com.ciscodeto.carregistro.core.presentation.composables.AppTopBar
import com.ciscodeto.carregistro.core.presentation.composables.DropdownMenuInfo
import com.ciscodeto.carregistro.core.presentation.composables.DropdownOptions
import com.ciscodeto.carregistro.core.ui.events.UiEvent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CarsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CarsListViewModel = koinViewModel(),
) {
    val cars by viewModel.cars.collectAsState()
    val manufacturers by viewModel.manufacturers.collectAsState()
    val carToEdit by viewModel.carToEdit.collectAsState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showForm by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowDeleteConfirmation -> {
                    showDeleteDialog = true
                }

                is UiEvent.ShowFormModal -> {
                    showForm = true
                }
            }
        }
    }

    if (showForm) {
        FormDialog(
            carToEdit = carToEdit,
            manufacturers = manufacturers,
            onDismiss = {
                showForm = false
                viewModel.onFormDismissed()
            },
            onConfirm = { car ->
                viewModel.confirmForm(car)
                showForm = false
            },
            title = viewModel.formTitle,
        )
    }

    if (showDeleteDialog) {
        ConfirmSelectionDialog(
            onDismiss = {
                showDeleteDialog = false
                viewModel.carIdToDelete = null
            },
            onConfirm = {
                viewModel.confirmDelete()
                showDeleteDialog = false
            },
            title = "Deletar veículo",
            message = "Tem certeza que deseja deletar este veículo?",
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppTopBar {
                DropdownOptions(
                    items = listOf(
                        DropdownMenuInfo(
                            text = "Sincronizar com externo",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = "Sincronizar",
                                    tint = MaterialTheme.colorScheme.onSurface
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
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            onClick = { viewModel.onCreateClicked() }
                        )
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
                    onDeleteClick = { viewModel.onDeleteClicked(car.id) },
                    onEditClick = { viewModel.onUpdateClicked(car) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}
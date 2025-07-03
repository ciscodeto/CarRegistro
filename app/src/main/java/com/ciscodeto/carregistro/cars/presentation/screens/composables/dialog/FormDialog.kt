package com.ciscodeto.carregistro.cars.presentation.screens.composables.dialog

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ciscodeto.carregistro.cars.presentation.model.CarUi
import com.ciscodeto.carregistro.cars.presentation.viewmodels.FormValidator
import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDialog(
    carToEdit: CarUi? = null,
    manufacturers: List<ManufacturerDto>,
    onDismiss: () -> Unit,
    onConfirm: (car: CarUi) -> Unit,
    title: String,
    errors: Set<FormValidator.FormErrors>
) {
    var car by remember(carToEdit) {
        mutableStateOf(
            carToEdit ?: CarUi(
                model = "",
                manufacturerId = 0,
                manufacturer = "",
                year = 0,
                motorization = "",
                idApi = null
            )
        )
    }

    var manufacturerExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val years = remember { (currentYear downTo 1970).toList() }

    val modelError = errors.contains(FormValidator.FormErrors.EMPTY_MODEL)
    val manufacturerError = errors.contains(FormValidator.FormErrors.EMPTY_MANUFACTURER)
    val yearError = errors.contains(FormValidator.FormErrors.EMPTY_YEAR)
    val motorizationError = errors.contains(FormValidator.FormErrors.EMPTY_MOTORIZATION)

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(Modifier.verticalScroll(rememberScrollState())) {
                OutlinedTextField(
                    value = car.model,
                    onValueChange = { car = car.copy(model = it) },
                    label = { Text("Modelo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = modelError,
                    supportingText = {
                        if (modelError) {
                            Text(
                                text = "Este campo é obrigatório",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExposedDropdownMenuBox(
                    expanded = manufacturerExpanded,
                    onExpandedChange = { manufacturerExpanded = !manufacturerExpanded }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryEditable)
                            .fillMaxWidth(),
                        readOnly = true,
                        value = car.manufacturer,
                        onValueChange = {},
                        label = { Text("Montadora") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = manufacturerExpanded) },
                        isError = manufacturerError,
                        supportingText = {
                            if (manufacturerError) {
                                Text(
                                    text = "Este campo é obrigatório",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = manufacturerExpanded,
                        onDismissRequest = { manufacturerExpanded = false }
                    ) {
                        manufacturers.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection.name) },
                                onClick = {
                                    car = car.copy(manufacturerId = selection.id, manufacturer = selection.name)
                                    manufacturerExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = yearExpanded,
                    onExpandedChange = { yearExpanded = !yearExpanded }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryEditable)
                            .fillMaxWidth(),
                        readOnly = true,
                        value = if (car.year == 0) "" else car.year.toString(),
                        onValueChange = {},
                        label = { Text("Ano") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = yearExpanded) },
                        isError = yearError,
                        supportingText = {
                            if (yearError) {
                                Text(
                                    text = "Este campo é obrigatório",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = yearExpanded,
                        onDismissRequest = { yearExpanded = false }
                    ) {
                        years.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection.toString()) },
                                onClick = {
                                    car = car.copy(year = selection)
                                    yearExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = car.motorization,
                    onValueChange = {
                        if (it.length <= 18) {
                            car = car.copy(motorization = it)
                        }
                    },
                    label = { Text("Motorização (ex: 1.6 Turbo)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = motorizationError,
                    supportingText = {
                        if (motorizationError) {
                            Text(
                                text = "Este campo é obrigatório",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cancelar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { car?.let { onConfirm(it) } }) {
                    Text("Confirmar")
                }
            }
        }
    }
}
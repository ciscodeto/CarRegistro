package com.ciscodeto.carregistro.cars.presentation.screens.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ciscodeto.carregistro.cars.presentation.model.CarUi

@Composable
fun CarListElement(carUi: CarUi, modifier: Modifier = Modifier) {
    Text(carUi.model)
}
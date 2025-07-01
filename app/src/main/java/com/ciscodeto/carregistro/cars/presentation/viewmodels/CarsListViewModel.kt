package com.ciscodeto.carregistro.cars.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ciscodeto.carregistro.cars.presentation.model.CarUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarsListViewModel : ViewModel() {
    private val _cars = MutableStateFlow<List<CarUi>>(emptyList())
    val cars: StateFlow<List<CarUi>> = _cars

}
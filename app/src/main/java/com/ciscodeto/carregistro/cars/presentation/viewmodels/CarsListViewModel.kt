package com.ciscodeto.carregistro.cars.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.carregistro.cars.application.car.delete.DeleteCar
import com.ciscodeto.carregistro.cars.application.car.getAll.GetCars
import com.ciscodeto.carregistro.cars.application.car.getAll.ImportApiCars
import com.ciscodeto.carregistro.cars.application.car.update.UpdateCar
import com.ciscodeto.carregistro.cars.presentation.model.CarUi
import com.ciscodeto.carregistro.cars.presentation.model.toDto
import com.ciscodeto.carregistro.cars.presentation.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarsListViewModel(
    private val getCars: GetCars,
    private val deleteCar: DeleteCar,
    private val updateCar: UpdateCar,
    private val importApiCars: ImportApiCars,
) : ViewModel() {
    private val _cars = MutableStateFlow<List<CarUi>>(emptyList())
    val cars: StateFlow<List<CarUi>> = _cars

    init {
        loadCars()
    }

    fun syncCars() {
        viewModelScope.launch { importApiCars.importCars() }
    }

    private fun loadCars() {
        viewModelScope.launch {
            getCars.getCars().collect { carsDto -> _cars.value = carsDto.map { dto -> dto.toUi() } }
        }
    }

    fun deleteCar(car: CarUi) {
        viewModelScope.launch { deleteCar.deleteCar(car.id) }
    }

    fun updateCar(car: CarUi) {
        viewModelScope.launch { updateCar.updateCar(car.toDto()) }
    }
}
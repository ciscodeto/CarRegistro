package com.ciscodeto.carregistro.cars.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciscodeto.carregistro.cars.application.car.create.CreateCar
import com.ciscodeto.carregistro.cars.application.car.delete.DeleteCar
import com.ciscodeto.carregistro.cars.application.car.getAll.GetCars
import com.ciscodeto.carregistro.cars.application.car.getAll.ImportApiCars
import com.ciscodeto.carregistro.cars.application.car.update.UpdateCar
import com.ciscodeto.carregistro.cars.presentation.model.CarUi
import com.ciscodeto.carregistro.cars.presentation.model.toDto
import com.ciscodeto.carregistro.cars.presentation.model.toUi
import com.ciscodeto.carregistro.cars.presentation.screens.composables.dialog.FormAction
import com.ciscodeto.carregistro.core.ui.events.UiEvent
import com.ciscodeto.carregistro.core.utils.Result
import com.ciscodeto.carregistro.manufacturers.getAll.GetManufacturers
import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CarsListViewModel(
    private val getCars: GetCars,
    private val deleteCar: DeleteCar,
    private val updateCar: UpdateCar,
    private val createCar: CreateCar,
    private val getManufacturers: GetManufacturers,
    private val importApiCars: ImportApiCars,
) : ViewModel() {
    private val _cars = MutableStateFlow<List<CarUi>>(emptyList())
    val cars: StateFlow<List<CarUi>> = _cars

    private val _manufacturers = MutableStateFlow<List<ManufacturerDto>>(emptyList())
    val manufacturers: StateFlow<List<ManufacturerDto>> = _manufacturers

    private val _carToEdit = MutableStateFlow<CarUi?>(null)
    val carToEdit: StateFlow<CarUi?> = _carToEdit

    var formTitle by mutableStateOf("")
    private var formValidator = FormValidator()

    private val _formErrors = MutableStateFlow<Set<FormValidator.FormErrors>>(emptySet())
    val formErrors: StateFlow<Set<FormValidator.FormErrors>> = _formErrors

    var carIdToDelete: Int? = null
    private var formAction by mutableStateOf(FormAction.CREATE)

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadCars()
        loadManufacturers()
    }

    fun syncCars() {
        viewModelScope.launch { importApiCars.importCars() }
    }

    private fun loadCars() {
        viewModelScope.launch {
            getCars.getCars().collect { carsDto -> _cars.value = carsDto.map { dto -> dto.toUi() } }
        }
    }

    private fun loadManufacturers() {
        viewModelScope.launch { _manufacturers.value = getManufacturers.getManufacturers() }
    }

    fun onDeleteClicked(id: Int) {
        carIdToDelete = id
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.ShowConfirmationModal)
        }
    }

    fun confirmDelete() {
        if (carIdToDelete == null) return
        viewModelScope.launch { deleteCar.deleteCar(carIdToDelete!!) }
        carIdToDelete = null
    }

    fun onUpdateClicked(car: CarUi) {
        _carToEdit.value = car
        formAction = FormAction.EDIT
        formTitle = "Editar veículo"
        showFormModal()
    }

    fun onCreateClicked() {
        formAction = FormAction.CREATE
        formTitle = "Adicionar veículo"
        showFormModal()
    }

    private fun showFormModal() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.ShowFormModal)
        }
    }

    fun confirmForm(car: CarUi) {
        val validationResults = formValidator.validate(car)
        val errors = validationResults.mapNotNull { it as? Result.Error }.map { it.error }

        if (errors.isNotEmpty()) {
            _formErrors.value = errors.toSet()
        } else {
            _formErrors.value = emptySet()
            if (formAction == FormAction.EDIT) {
                confirmUpdate(car)
            } else {
                confirmCreate(car)
            }
            onFormDismissed()
        }
    }

    private fun confirmUpdate(car: CarUi) {
        viewModelScope.launch { updateCar.updateCar(car.toDto()) }
    }

    private fun confirmCreate(car: CarUi) {
        viewModelScope.launch { createCar.createCar(car.toDto()) }
    }

    fun onFormDismissed() {
        _carToEdit.value = null
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.CloseFormModal)
        }
    }
}
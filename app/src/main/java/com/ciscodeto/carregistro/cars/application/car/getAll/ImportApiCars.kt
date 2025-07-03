package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.presentation.viewmodels.ApiErrors
import com.ciscodeto.carregistro.core.utils.Result

interface ImportApiCars {
    suspend fun importCars() : Result<Unit, ApiErrors>
}
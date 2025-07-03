package com.ciscodeto.carregistro.manufacturers.getAll

import com.ciscodeto.carregistro.cars.presentation.viewmodels.ApiErrors
import com.ciscodeto.carregistro.core.utils.Result

interface GetManufacturers {
    suspend fun getManufacturers(): Result<List<ManufacturerDto>, ApiErrors>
}
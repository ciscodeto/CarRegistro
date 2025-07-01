package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import kotlinx.coroutines.flow.Flow

interface GetCars {
    suspend fun getCars(): Flow<List<CarDto>>
}
package com.ciscodeto.carregistro.cars.application.car.repository

import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun getCars(): Flow<List<CarDto>>
}
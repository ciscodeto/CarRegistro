package com.ciscodeto.carregistro.cars.application.car.repository

import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun findAll(): Flow<List<CarDto>>
    suspend fun insert(car: CarDto)
    suspend fun update(car: CarDto)
    suspend fun delete(id: Int)
}
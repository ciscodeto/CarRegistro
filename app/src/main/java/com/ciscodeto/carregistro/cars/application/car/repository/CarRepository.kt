package com.ciscodeto.carregistro.cars.application.car.repository

interface CarRepository {
    suspend fun getCars(): List<CarDto>
}
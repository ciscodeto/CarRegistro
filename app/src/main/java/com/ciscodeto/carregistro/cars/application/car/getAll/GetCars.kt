package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

interface GetCars {
    suspend fun getAllCars(): List<CarDto>
    suspend fun getApiCars(): List<CarDto>
}
package com.ciscodeto.carregistro.cars.application.car.create

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

interface CreateCar {
    suspend fun createCar(carDto: CarDto)
}
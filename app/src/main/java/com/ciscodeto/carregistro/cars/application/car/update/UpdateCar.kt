package com.ciscodeto.carregistro.cars.application.car.update

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

interface UpdateCar {
    suspend fun updateCar(carDto: CarDto)
}
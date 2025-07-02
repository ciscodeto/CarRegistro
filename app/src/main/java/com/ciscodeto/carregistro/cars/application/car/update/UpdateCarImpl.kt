package com.ciscodeto.carregistro.cars.application.car.update

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository

class UpdateCarImpl(
    private val carRepository: CarRepository,
) : UpdateCar {
    override suspend fun updateCar(carDto: CarDto) {
        carRepository.update(carDto)
    }
}

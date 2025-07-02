package com.ciscodeto.carregistro.cars.application.car.create

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository

class CreateCarImpl(
    private val carRepository: CarRepository
) : CreateCar {
    override suspend fun createCar(carDto: CarDto) {
        carRepository.insert(carDto)
    }
}
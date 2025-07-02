package com.ciscodeto.carregistro.cars.application.car.delete

import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository

class DeleteCarImpl(
    private val carRepository: CarRepository,
) : DeleteCar {
    override suspend fun deleteCar(id: Int) {
        carRepository.delete(id)
    }
}
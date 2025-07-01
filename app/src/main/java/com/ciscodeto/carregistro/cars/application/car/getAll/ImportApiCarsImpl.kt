package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository

class ImportApiCarsImpl(
    private val manufacturerRepository: ManufacturerRepository,
    private val carRepository: CarRepository,
) : ImportApiCars {
    override suspend fun importCars() {
        TODO("Not yet implemented")
    }
}
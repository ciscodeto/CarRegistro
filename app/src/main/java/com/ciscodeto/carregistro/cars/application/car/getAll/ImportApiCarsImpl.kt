package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import com.ciscodeto.carregistro.infrastructure.api.CarApiService
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository

class ImportApiCarsImpl(
    private val carRepository: CarRepository,
    private val carApiService: CarApiService,
) : ImportApiCars {
    override suspend fun importCars() {
        val cars = carApiService.getCars().map { it.toDto() }
        cars.forEach {
            val idApi = it.idApi
            val existing = carRepository.findByApiId(idApi!!)

            if (existing == null) {
                carRepository.insert(it.copy(id = 0))
            } else {
                carRepository.update(it.copy(id = existing.id))
            }
        }
    }
}
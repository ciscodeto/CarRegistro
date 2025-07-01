package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class GetCarsImpl(
    private val repository: CarRepository,
) : GetCars {
    override suspend fun getCars(): Flow<List<CarDto>> {
        return repository.getCars()
    }
}
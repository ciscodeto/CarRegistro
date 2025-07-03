package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCarsImpl(
    private val carRepository: CarRepository,
    private val manufacturerRepository: ManufacturerRepository,
) : GetCars {
    override suspend fun getCars(): Flow<List<CarDto>> {
        val manufacturers = manufacturerRepository.getManufacturers()

        return carRepository.findAll().map { carList ->
            carList.map { car ->
                car.copy(
                    manufacturer = manufacturers.find { it.id == car.manufacturerId }?.name
                        ?: "Montadora não encontrada"
                )
            }
        }
    }
}
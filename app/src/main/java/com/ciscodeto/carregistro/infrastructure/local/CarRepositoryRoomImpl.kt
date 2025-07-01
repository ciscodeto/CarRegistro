package com.ciscodeto.carregistro.infrastructure.local

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryRoomImpl(
    private val dao: CarDao,
) : CarRepository {
    override suspend fun findAll(): Flow<List<CarDto>> {
        return dao.getAll().map { entities ->
            entities.map { it.toDto() }
        }
    }

    override suspend fun insert(car: CarDto) {
        dao.insert(car.toEntity())
    }

    override suspend fun update(car: CarDto) {
        dao.upsert(car.toEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }
}
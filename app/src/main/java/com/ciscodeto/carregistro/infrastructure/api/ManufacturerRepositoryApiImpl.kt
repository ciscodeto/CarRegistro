package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository

class ManufacturerRepositoryApiImpl(
    private val carApiService: CarApiService
) : ManufacturerRepository {
    override suspend fun getManufacturers(): List<ManufacturerDto> {
        return try {
             carApiService.getManufacturers()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
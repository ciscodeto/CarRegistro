package com.ciscodeto.carregistro.manufacturers.getAll

import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository

class GetManufacturersImpl(
    private val repository: ManufacturerRepository
) : GetManufacturers {
    override suspend fun getManufacturers(): List<ManufacturerDto> {
        return repository.getManufacturers()
    }
}
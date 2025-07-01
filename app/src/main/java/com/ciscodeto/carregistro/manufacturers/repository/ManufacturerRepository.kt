package com.ciscodeto.carregistro.manufacturers.repository

import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto

interface ManufacturerRepository {
    suspend fun getManufacturers(): List<ManufacturerDto>
}
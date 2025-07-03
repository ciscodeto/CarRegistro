package com.ciscodeto.carregistro.manufacturers.getAll

interface GetManufacturers {
    suspend fun getManufacturers(): List<ManufacturerDto>
}
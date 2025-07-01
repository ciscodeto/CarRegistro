package com.ciscodeto.carregistro.infrastructure.local

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

fun CarEntity.toDto() = CarDto(
    id = id,
    idApi = idApi,
    model = model,
    manufacturerId = idManufacturer,
    manufacturer = null,
    year = year,
    motorization = motorization
)

fun CarDto.toEntity() = CarEntity(
    id = id,
    idApi = idApi,
    model = model,
    idManufacturer = manufacturerId,
    year = year,
    motorization = motorization
)
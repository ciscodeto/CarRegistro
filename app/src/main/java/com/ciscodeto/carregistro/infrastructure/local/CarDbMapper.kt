package com.ciscodeto.carregistro.infrastructure.local

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

fun CarEntity.toDto() = CarDto(
    id = id,
    idApi = idApi,
    model = model,
    idManufacturer = idManufacturer,
    year = year,
    motorization = motorization
)
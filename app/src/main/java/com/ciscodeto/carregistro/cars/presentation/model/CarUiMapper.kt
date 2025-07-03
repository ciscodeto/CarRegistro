package com.ciscodeto.carregistro.cars.presentation.model

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto

fun CarDto.toUi() = CarUi(
    id = id,
    idApi = idApi,
    model = model,
    manufacturerId = manufacturerId,
    manufacturer = manufacturer ?: "Montadora não encontrada",
    year = year,
    motorization = motorization,
)

fun CarUi.toDto() = CarDto(
    id = id,
    idApi = idApi,
    model = model,
    manufacturerId = manufacturerId,
    manufacturer = manufacturer,
    year = year,
    motorization = motorization,
)
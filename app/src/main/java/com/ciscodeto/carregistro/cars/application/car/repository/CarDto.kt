package com.ciscodeto.carregistro.cars.application.car.repository

data class CarDto (
    val id: Int,
    val idApi: Int?,
    val model: String,
    val idManufacturer: Int,
    val year: Int,
    val motorization: Float,
)
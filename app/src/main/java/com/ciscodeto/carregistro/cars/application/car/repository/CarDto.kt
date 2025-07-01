package com.ciscodeto.carregistro.cars.application.car.repository

import kotlinx.serialization.Serializable

@Serializable
data class CarDto (
    val id: Int,
    val idApi: Int?,
    val model: String,
    val manufacturerId: Int,
    val manufacturer: String?,
    val year: Int,
    val motorization: Float,
)
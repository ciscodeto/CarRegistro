package com.ciscodeto.carregistro.cars.presentation.model

data class CarUi(
    val id: Int,
    val model: String,
    val manufacturerId: Int,
    val manufacturer: String,
    val year: Int,
    val motorization: String,
)

package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.cars.application.car.repository.CarDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarResponse(
    @SerialName("ano")val year: Int,
    @SerialName("id") val id: Int,
    @SerialName("id_montadora") val manufacturerId: Int,
    @SerialName("modelo") val model: String,
    @SerialName("motorizacao") val motorization: Float
) {
    fun toDto() = CarDto(
        id = 0,
        idApi = id,
        model = model,
        manufacturerId = manufacturerId,
        manufacturer = null,
        year = year,
        motorization = motorization,
    )
}
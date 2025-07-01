package com.ciscodeto.carregistro.manufacturers.getAll

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ManufacturerDto(
    @SerialName("id") val id: Int,
    @SerialName("nome") val name: String,
)

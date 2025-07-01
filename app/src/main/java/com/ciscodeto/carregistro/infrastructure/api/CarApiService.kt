package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CarApiService(
    private val client: HttpClient
) {
    suspend fun getManufacturers(): List<ManufacturerDto> {
        return client.get("manufactures")
            .body()
    }

    suspend fun getCars(): List<CarResponse> {
        return client.get("vehicles")
            .body()
    }
}
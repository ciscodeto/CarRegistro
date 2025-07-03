package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.manufacturers.getAll.ManufacturerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class CarApiService(
    private val client: HttpClient
) {
    suspend fun getManufacturers(): List<ManufacturerDto> {
        val manufacturers = client.get("manufactures")
        if (manufacturers.status == HttpStatusCode.OK) {
            return manufacturers.body()
        } else {
            throw Exception("Error fetching manufacturers")
        }
    }

    suspend fun getCars(): List<CarResponse> {
        val cars = client.get("vehicles")
        if (cars.status == HttpStatusCode.OK) {
            return cars.body()
        } else {
            throw Exception("Error fetching cars")
        }
    }
}
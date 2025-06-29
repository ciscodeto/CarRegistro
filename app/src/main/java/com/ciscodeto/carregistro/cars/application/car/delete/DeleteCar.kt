package com.ciscodeto.carregistro.cars.application.car.delete

interface DeleteCar {
    suspend fun deleteCar(id: Int)
}
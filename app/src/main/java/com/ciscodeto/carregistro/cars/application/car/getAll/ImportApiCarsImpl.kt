package com.ciscodeto.carregistro.cars.application.car.getAll

import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import com.ciscodeto.carregistro.cars.presentation.viewmodels.ApiErrors
import com.ciscodeto.carregistro.core.utils.Result
import com.ciscodeto.carregistro.infrastructure.api.ApiException
import com.ciscodeto.carregistro.infrastructure.api.CarApiService
import com.ciscodeto.carregistro.infrastructure.api.EmptyResponseException
import com.ciscodeto.carregistro.infrastructure.api.NoInternetException

class ImportApiCarsImpl(
    private val carRepository: CarRepository,
    private val carApiService: CarApiService,
) : ImportApiCars {
    override suspend fun importCars(): Result<Unit, ApiErrors> {
        return try {
            val cars = carApiService.getCars().map { it.toDto() }
            cars.forEach {
                val idApi = it.idApi
                val existing = carRepository.findByApiId(idApi!!)

                if (existing == null) {
                    carRepository.insert(it.copy(id = 0))
                } else {
                    carRepository.update(it.copy(id = existing.id))
                }
            }
            Result.Success(Unit)
        } catch (e: NoInternetException) {
            Result.Error(ApiErrors.Network.NO_INTERNET_CONNECTION)
        } catch (e: ApiException) {
            Result.Error(ApiErrors.Data.UNEXPECTED_ERROR)
        } catch (e: EmptyResponseException) {
            Result.Error(ApiErrors.Data.NOT_FOUND)
        }
    }
}
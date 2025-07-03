package com.ciscodeto.carregistro.manufacturers.getAll

import com.ciscodeto.carregistro.cars.presentation.viewmodels.ApiErrors
import com.ciscodeto.carregistro.core.utils.Result
import com.ciscodeto.carregistro.infrastructure.api.ApiException
import com.ciscodeto.carregistro.infrastructure.api.EmptyResponseException
import com.ciscodeto.carregistro.infrastructure.api.NoInternetException
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository

class GetManufacturersImpl(
    private val repository: ManufacturerRepository
) : GetManufacturers {
    override suspend fun getManufacturers(): Result<List<ManufacturerDto>, ApiErrors> {
        return try {
            Result.Success(repository.getManufacturers())
        } catch (e: NoInternetException) {
            Result.Error(ApiErrors.Network.NO_INTERNET_CONNECTION)
        } catch (e: EmptyResponseException) {
            Result.Error(ApiErrors.Data.NOT_FOUND)
        } catch (e: Exception) {
            Result.Error(ApiErrors.Data.UNEXPECTED_ERROR)
        }
    }
}
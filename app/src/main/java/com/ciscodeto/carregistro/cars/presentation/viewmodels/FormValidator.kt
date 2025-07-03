package com.ciscodeto.carregistro.cars.presentation.viewmodels

import com.ciscodeto.carregistro.cars.presentation.model.CarUi
import com.ciscodeto.carregistro.core.utils.Error
import com.ciscodeto.carregistro.core.utils.Result

class FormValidator {
    fun validate(car: CarUi): List<Result<CarUi, FormErrors>> {
        val validationResults = mutableListOf<Result<CarUi, FormErrors>>()
        if (car.manufacturerId == 0) {
            validationResults.add(Result.Error(FormErrors.EMPTY_MANUFACTURER))
        }
        if (car.model.isEmpty()) {
            validationResults.add(Result.Error(FormErrors.EMPTY_MODEL))
        }
        if (car.year == 0) {
            validationResults.add(Result.Error(FormErrors.EMPTY_YEAR))
        }
        if (car.motorization.isEmpty()) {
            validationResults.add(Result.Error(FormErrors.EMPTY_MOTORIZATION))
        }
        if (validationResults.isEmpty()) {
            validationResults.add(Result.Success(car))
        }
        return validationResults
    }

    enum class FormErrors: Error {
        EMPTY_MANUFACTURER,
        EMPTY_MODEL,
        EMPTY_YEAR,
        EMPTY_MOTORIZATION
    }
}
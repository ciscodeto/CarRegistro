package com.ciscodeto.carregistro.cars.presentation.viewmodels

import com.ciscodeto.carregistro.core.utils.Error

interface ApiErrors : Error {
    enum class Network : ApiErrors {
        NO_INTERNET_CONNECTION
    }
    enum class Data : ApiErrors {
        NOT_FOUND,
        UNEXPECTED_ERROR
    }
}
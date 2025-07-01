package com.ciscodeto.carregistro.di

import com.ciscodeto.carregistro.cars.application.car.getAll.GetCars
import com.ciscodeto.carregistro.cars.application.car.getAll.GetCarsImpl
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import com.ciscodeto.carregistro.manufacturers.getAll.GetManufacturers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val carsModule = module {
    single<GetCars> { GetCarsImpl(get(), get()) }
}

val manufacturersModule = module {
    single<GetManufacturers> { GetManufacturersImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        CarsListViewModel()
    }
}

fun appModules() = listOf(
    viewModelModule,
    carsModule
)
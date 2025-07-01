package com.ciscodeto.carregistro.di

import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CarsListViewModel()
    }
}

fun appModules() = listOf(
    viewModelModule
)
package com.ciscodeto.carregistro.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ciscodeto.carregistro.cars.application.car.create.CreateCar
import com.ciscodeto.carregistro.cars.application.car.create.CreateCarImpl
import com.ciscodeto.carregistro.cars.application.car.delete.DeleteCar
import com.ciscodeto.carregistro.cars.application.car.delete.DeleteCarImpl
import com.ciscodeto.carregistro.cars.application.car.getAll.GetCars
import com.ciscodeto.carregistro.cars.application.car.getAll.GetCarsImpl
import com.ciscodeto.carregistro.cars.application.car.getAll.ImportApiCars
import com.ciscodeto.carregistro.cars.application.car.getAll.ImportApiCarsImpl
import com.ciscodeto.carregistro.cars.application.car.repository.CarRepository
import com.ciscodeto.carregistro.cars.application.car.update.UpdateCar
import com.ciscodeto.carregistro.cars.application.car.update.UpdateCarImpl
import com.ciscodeto.carregistro.cars.presentation.viewmodels.CarsListViewModel
import com.ciscodeto.carregistro.infrastructure.api.CarApiService
import com.ciscodeto.carregistro.infrastructure.api.ManufacturerRepositoryApiImpl
import com.ciscodeto.carregistro.infrastructure.api.provideHttpClient
import com.ciscodeto.carregistro.infrastructure.local.AppDatabase
import com.ciscodeto.carregistro.infrastructure.local.car.CarDao
import com.ciscodeto.carregistro.infrastructure.local.car.CarRepositoryRoomImpl
import com.ciscodeto.carregistro.manufacturers.getAll.GetManufacturers
import com.ciscodeto.carregistro.manufacturers.getAll.GetManufacturersImpl
import com.ciscodeto.carregistro.manufacturers.repository.ManufacturerRepository
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single<HttpClient> { provideHttpClient() }
    single<CarApiService> { CarApiService(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
                context = get(),
                klass = AppDatabase::class.java,
                name = "cars.db"
            ).fallbackToDestructiveMigration(false)
            .build()
    }

    single<CarDao> { get<AppDatabase>().carDao() }
}

val carsModule = module {
    single<CarRepository> { CarRepositoryRoomImpl(get()) }
    single<GetCars> { GetCarsImpl(get(), get()) }
    single<UpdateCar> { UpdateCarImpl(get()) }
    single<CreateCar> { CreateCarImpl(get()) }
    single<DeleteCar> { DeleteCarImpl(get()) }
    single<ImportApiCars> { ImportApiCarsImpl(get(), get()) }
}

val manufacturersModule = module {
    single<ManufacturerRepository> { ManufacturerRepositoryApiImpl(get()) }
    single<GetManufacturers> { GetManufacturersImpl(get()) }
}

val viewModelModule = module {
    viewModel {
        CarsListViewModel(get(), get(), get(), get())
    }
}

fun appModules() = listOf(
    viewModelModule,
    carsModule,
    databaseModule,
    manufacturersModule,
    apiModule
)
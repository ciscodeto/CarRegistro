package com.ciscodeto.carregistro.infrastructure.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ciscodeto.carregistro.infrastructure.local.car.CarDao
import com.ciscodeto.carregistro.infrastructure.local.car.CarEntity

@Database(
    entities = [CarEntity::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}
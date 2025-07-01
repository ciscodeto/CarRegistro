package com.ciscodeto.carregistro.infrastructure.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idApi: Int? = null,
    val model: String,
    val idManufacturer: Int,
    val year: Int,
    val motorization: Float,
)
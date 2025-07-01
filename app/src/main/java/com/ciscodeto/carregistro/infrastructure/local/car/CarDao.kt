package com.ciscodeto.carregistro.infrastructure.local.car

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAll(): Flow<List<CarEntity>>

    @Query("SELECT * FROM cars WHERE id = :id")
    suspend fun getById(id: Int): CarEntity?

    @Query("SELECT * FROM cars WHERE idApi = :apiId")
    suspend fun getByApiId(apiId: Int): CarEntity?

    @Insert
    suspend fun insert(car: CarEntity)

    @Upsert
    suspend fun upsert(car: CarEntity)

    @Query("DELETE FROM cars WHERE id = :id")
    suspend fun delete(id: Int)
}
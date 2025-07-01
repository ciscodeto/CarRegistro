package com.ciscodeto.carregistro.infrastructure.local

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
    suspend fun getById(id: ByteArray): CarEntity?

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insert(character: CarEntity)

    @Upsert
    suspend fun upsert(character: CarEntity)

    @Query("DELETE FROM cars WHERE id = :id")
    suspend fun delete(id: ByteArray)
}
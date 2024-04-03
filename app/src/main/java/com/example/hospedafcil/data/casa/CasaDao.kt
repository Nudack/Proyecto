package com.example.hospedafcil.data.casa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CasaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(casa: Casa)

    @Update
    suspend fun update(casa: Casa)

    @Delete
    suspend fun delete(casa: Casa)

    @Query("SELECT * FROM casa WHERE id = :id")
    fun getCasa(id: Int): Flow<Casa>

    @Query("SELECT * FROM casa ORDER BY nombre ASC")
    fun getAllCasas(): Flow<List<Casa>>
}
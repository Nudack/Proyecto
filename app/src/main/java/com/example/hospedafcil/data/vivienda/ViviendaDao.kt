package com.example.hospedafcil.data.vivienda

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ViviendaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVivienda(vivienda: Vivienda)

    @Update
    suspend fun updateVivienda(vivienda: Vivienda)

    @Delete
    suspend fun deleteVivienda(vivienda: Vivienda)

    @Query("SELECT * FROM vivienda WHERE id = :id")
    fun getVivienda(id: Int): Flow<Vivienda>

    @Query("SELECT * FROM vivienda ORDER BY nombre ASC")
    fun getAllViviendas(): Flow<List<Vivienda>>
}
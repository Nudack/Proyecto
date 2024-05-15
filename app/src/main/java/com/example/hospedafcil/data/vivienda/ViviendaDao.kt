package com.example.hospedafcil.data.vivienda

import android.graphics.Bitmap
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

    @Query("SELECT * FROM vivienda WHERE vivienda_id = :id")
    fun getVivienda(id: Int): Flow<Vivienda>

    @Query("SELECT * FROM vivienda ORDER BY vivienda_nombre ASC")
    fun getAllViviendas(): Flow<List<Vivienda>>

    @Query("Select * from vivienda where vivienda_tipo = :tipo")
    fun getAllCasas(tipo: String): Flow<List<Vivienda>>

    @Query("Select * from vivienda where vivienda_tipo = :tipo")
    fun getAllHabitaciones(tipo: String): Flow<List<Vivienda>>

    @Query("Select * from vivienda where vivienda_tipo = :tipo")
    fun getAllApartamentos(tipo: String): Flow<List<Vivienda>>
}
package com.example.hospedafcil.data.nota

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNota(nota: Nota)

    @Update
    suspend fun updateNota(nota: Nota)

    @Delete
    suspend fun deleteNota(nota: Nota)

    @Query("SELECT * FROM nota WHERE nota_id = :id")
    fun getNota(id: Int): Flow<Nota>

    @Query("SELECT * FROM nota ORDER BY nota_id ASC")
    fun getAllNotas(): Flow<List<Nota>>

    @Query("SELECT * FROM nota WHERE vivienda_id = :viviendaId")
    fun getNotasByVivienda(viviendaId: Int): Flow<List<Nota>>
}
package com.example.hospedafcil.data.inventario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InventarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInventario(inventario: Inventario)

    @Update
    suspend fun updateInventario(inventario: Inventario)

    @Delete
    suspend fun deleteInventario(inventario: Inventario)

    @Query("SELECT * FROM inventario WHERE id = :id")
    fun getInventario(id: Int): Flow<Inventario>

    @Query("SELECT * FROM inventario ORDER BY id ASC")
    fun getAllInventario(): Flow<List<Inventario>>
}
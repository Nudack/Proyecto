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
    suspend fun insertItem(inventario: Item)

    @Update
    suspend fun updateItem(inventario: Item)

    @Delete
    suspend fun deleteItem(inventario: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * FROM item ORDER BY id ASC")
    fun getInventario(): Flow<List<Item>>
}
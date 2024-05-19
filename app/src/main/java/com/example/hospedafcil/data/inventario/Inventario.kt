package com.example.hospedafcil.data.inventario

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
)
package com.example.hospedafcil.data.inventario

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventario")
data class Inventario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
)
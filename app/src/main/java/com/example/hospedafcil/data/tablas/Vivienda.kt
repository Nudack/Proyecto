package com.example.hospedafcil.data.tablas

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vivienda")
data class Vivienda(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tipo: String,
    val nombre: String,
    val descripcion: String,
    val imagen: Bitmap?
)
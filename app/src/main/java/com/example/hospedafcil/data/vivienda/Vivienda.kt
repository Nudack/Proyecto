package com.example.hospedafcil.data.vivienda

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vivienda")
data class Vivienda(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: String,
    val nombre: String,
    val descripcion: String,
    val imagen: Bitmap?
)
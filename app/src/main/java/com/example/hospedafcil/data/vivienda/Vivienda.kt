package com.example.hospedafcil.data.vivienda

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vivienda")
data class Vivienda(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vivienda_id")
    val id: Int = 0,
    @ColumnInfo(name = "vivienda_tipo")
    val tipo: String,
    @ColumnInfo(name = "vivienda_nombre")
    val nombre: String,
    @ColumnInfo(name = "vivienda_descripcion")
    val descripcion: String,
    @ColumnInfo(name = "vivienda_imagen")
    val imagen: Bitmap?
)
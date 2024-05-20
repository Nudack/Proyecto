package com.example.hospedafcil.data.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nota",)
data class Nota(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nota_id")
    val id: Int = 0,
    @ColumnInfo(name = "nota_asunto")
    val asunto: String,
    @ColumnInfo(name = "nota_descripcion")
    val descripcion: String
)
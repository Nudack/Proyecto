package com.example.hospedafcil.data.nota

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.hospedafcil.data.vivienda.Vivienda

@Entity(
    tableName = "nota",
    foreignKeys = [
        ForeignKey(
            entity = Vivienda::class,
            parentColumns = arrayOf("vivienda_id"),
            childColumns = arrayOf("vivienda_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
            )
    ]
    )
data class Nota(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nota_id")
    val id: Int = 0,
    @ColumnInfo(name = "nota_asunto")
    val asunto: String,
    @ColumnInfo(name = "nota_descripcion")
    val descripcion: String,
    @ColumnInfo(name = "vivienda_id")
    val idVivienda: Int,
)
package com.example.hospedafcil.data.casa

import androidx.room.Entity
import androidx.room.PrimaryKey


//entidad Casa para las casas que se quieran guardar en la app
@Entity(tableName = "casa")
data class Casa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val imagen: Int
)
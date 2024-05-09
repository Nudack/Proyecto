package com.example.hospedafcil.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hospedafcil.data.typeConverters.BitmapTypeConverter
import com.example.hospedafcil.data.vivienda.Vivienda
import com.example.hospedafcil.data.vivienda.ViviendaDao

@Database (entities = [Vivienda::class, ], version = 1, exportSchema = false)
@TypeConverters(BitmapTypeConverter::class)
abstract class BaseDeDatos: RoomDatabase() {
    abstract fun viviendaDao(): ViviendaDao
}
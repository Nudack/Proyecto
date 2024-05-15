package com.example.hospedafcil.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hospedafcil.data.inventario.Inventario
import com.example.hospedafcil.data.inventario.InventarioDao
import com.example.hospedafcil.data.nota.Nota
import com.example.hospedafcil.data.nota.NotaDao
import com.example.hospedafcil.data.typeConverters.BitmapTypeConverter
import com.example.hospedafcil.data.vivienda.Vivienda
import com.example.hospedafcil.data.vivienda.ViviendaDao

@Database (entities = [Vivienda::class, Nota::class, Inventario::class], version = 1, exportSchema = false)
@TypeConverters(BitmapTypeConverter::class)
abstract class BaseDeDatos: RoomDatabase() {
    abstract fun viviendaDao(): ViviendaDao

    abstract fun notaDao(): NotaDao

    abstract fun inventarioDao(): InventarioDao
}
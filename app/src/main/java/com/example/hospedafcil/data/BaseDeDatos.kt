package com.example.hospedafcil.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hospedafcil.data.daos.InventarioDao
import com.example.hospedafcil.data.daos.NotaDao
import com.example.hospedafcil.data.daos.ViviendaDao
import com.example.hospedafcil.data.tablas.Item
import com.example.hospedafcil.data.tablas.Nota
import com.example.hospedafcil.data.tablas.Vivienda
import com.example.hospedafcil.data.typeConverters.BitmapTypeConverter

@Database (entities = [Vivienda::class, Nota::class, Item::class], version = 1, exportSchema = false)
@TypeConverters(BitmapTypeConverter::class)
abstract class BaseDeDatos: RoomDatabase() {
    abstract fun viviendaDao(): ViviendaDao

    abstract fun notaDao(): NotaDao

    abstract fun inventarioDao(): InventarioDao
}
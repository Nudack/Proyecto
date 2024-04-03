package com.example.hospedafcil.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hospedafcil.data.casa.Casa
import com.example.hospedafcil.data.casa.CasaDao

@Database (entities = [Casa::class], version = 1, exportSchema = false)
abstract class BaseDeDatos: RoomDatabase() {
    abstract fun CasaDao(): CasaDao
    companion object {
        @Volatile
        private var Instance: BaseDeDatos? = null

        fun getDatabase(context: Context): BaseDeDatos {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BaseDeDatos::class.java, "base_de_datos")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
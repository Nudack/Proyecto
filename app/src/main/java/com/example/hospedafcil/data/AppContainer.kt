package com.example.hospedafcil.data

import android.content.Context
import com.example.hospedafcil.data.casa.CasaRepository
import com.example.hospedafcil.data.casa.OfflineCasaRepository

interface AppContainer {
    val casaRepository: CasaRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val casaRepository: CasaRepository by lazy {
        OfflineCasaRepository(BaseDeDatos.getDatabase(context).CasaDao())
    }
}
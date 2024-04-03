package com.example.hospedafcil

import com.example.hospedafcil.data.AppContainer
import com.example.hospedafcil.data.AppDataContainer
import android.app.Application

class HospedaFacilApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
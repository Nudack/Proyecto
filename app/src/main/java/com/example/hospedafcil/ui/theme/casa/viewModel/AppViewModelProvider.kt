package com.example.hospedafcil.ui.theme.casa.viewModel

import android.text.Spannable.Factory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.hospedafcil.HospedaFacilApplication
import com.example.hospedafcil.ui.theme.hospedaFacil.ui.HospedaFacilApp

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CasaViewModel(HospedaFacilApplication().container.casaRepository)
        }
    }
}
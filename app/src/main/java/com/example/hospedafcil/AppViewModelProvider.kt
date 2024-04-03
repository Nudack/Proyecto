package com.example.hospedafcil

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.createSavedStateHandle
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaEditViewModel
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaViewModel

/**object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            CasaEditViewModel(
                this.createSavedStateHandle(),
                HospedaFacilApplication().container.casaRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            CasaViewModel(HospedaFacilApplication().container.casaRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                HospedaFacilApplication().container.casaRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(HospedaFacilApplication().container.casaRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): HospedaFacilApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HospedaFacilApplication)*/
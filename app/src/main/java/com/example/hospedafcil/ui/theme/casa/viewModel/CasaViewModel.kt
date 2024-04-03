package com.example.hospedafcil.ui.theme.casa.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hospedafcil.data.casa.Casa
import com.example.hospedafcil.data.casa.CasaRepository

class CasaViewModel(private val casaRepository: CasaRepository) : ViewModel() {
    var CasaUiState by mutableStateOf(CasaUiState())
        private set

    fun updateUiState(casaDetails: CasaDetails){
        CasaUiState = CasaUiState(casaDetails = casaDetails, isEntryValid = validateInput(casaDetails))
    }

    private fun validateInput(uiState: CasaDetails = CasaUiState.casaDetails): Boolean {
        return with(uiState) {
            nombre.isNotBlank() && descripcion.isNotBlank() && imagen.toString().isNotBlank()
        }
    }

    suspend fun saveCasa() {
        if (validateInput()) {
            casaRepository.insertCasa(CasaUiState.casaDetails.toCasa())
        }
    }
}

data class CasaUiState (
    val casaDetails: CasaDetails = CasaDetails(),
    val isEntryValid: Boolean = false
)

data class CasaDetails(
    val id: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val imagen: Int = 0
)

fun CasaDetails.toCasa(): Casa = Casa(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    imagen = imagen
)

fun Casa.toCasaUiState(isEntryValid: Boolean = false): CasaUiState = CasaUiState(
    casaDetails = this.toCasaDetails(),
    isEntryValid = isEntryValid
)

fun Casa.toCasaDetails(): CasaDetails = CasaDetails(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    imagen = imagen
)

package com.example.hospedafcil.ui.theme.casa.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hospedafcil.data.casa.CasaRepository
import kotlinx.coroutines.flow.StateFlow

class CasaDetailsViewModel (
    savedStateHandle: SavedStateHandle,
    private val casaRepository: CasaRepository
) : ViewModel() {
    private val casaId: Int = checkNotNull(savedStateHandle[CasaDetailsDestination.CasaIdArg])

    val uiState: StateFlow<CasaDetailsUiState> =
        CasaRepository.
}

data class CasaDetailsUiState(
    val casaDetails: CasaDetails = CasaDetails()
)
package com.example.hospedafcil.ui.theme.casa.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospedafcil.data.vivienda.Vivienda
import com.example.hospedafcil.data.vivienda.ViviendaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViviendasViewModel (
    private val viviendaDao: ViviendaDao,
): ViewModel() {
    var openDialog by mutableStateOf(false)
    val viviendas = viviendaDao.getAllViviendas()

    fun addVivienda(vivienda: Vivienda) {
        viewModelScope.launch(Dispatchers.IO) {
            viviendaDao.insertVivienda(vivienda)
        }
    }

    fun deleteVivienda(vivienda: Vivienda){
        viewModelScope.launch (Dispatchers.IO) {
            viviendaDao.deleteVivienda(vivienda)
        }
    }

    fun updateVivienda(vivienda: Vivienda) {
        viewModelScope.launch (Dispatchers.IO) {
            viviendaDao.updateVivienda(vivienda)
        }
    }

    fun closeDialog() {
        openDialog = false
    }

    fun openDialog() {
        openDialog = true
    }
}
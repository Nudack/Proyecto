package com.example.hospedafcil.data

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospedafcil.data.inventario.InventarioDao
import com.example.hospedafcil.data.inventario.Item
import com.example.hospedafcil.data.nota.Nota
import com.example.hospedafcil.data.nota.NotaDao
import com.example.hospedafcil.data.vivienda.Vivienda
import com.example.hospedafcil.data.vivienda.ViviendaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel (
    private val viviendaDao: ViviendaDao,
    private val notaDao: NotaDao,
    private val inventarioDao: InventarioDao
): ViewModel() {
    var vivienda by mutableStateOf(Vivienda(0, "", "", "", null))
    var openDialog by mutableStateOf(false)
    val viviendas = viviendaDao.getAllViviendas()
    val casas = viviendaDao.getViviendasPorTipo("Casa")
    val habitaciones = viviendaDao.getViviendasPorTipo("Habitación")
    val apartamentos = viviendaDao.getViviendasPorTipo("Apartamento")

    fun addVivienda(vivienda: Vivienda) {
        viewModelScope.launch(Dispatchers.IO) {
            viviendaDao.insertVivienda(vivienda)
        }
    }

    fun getVivienda(id: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            viviendaDao.getVivienda(id)
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

    fun updateViviendaNombre(nombre: String) {
        vivienda = vivienda.copy(nombre = nombre)
    }

    fun updateViviendaTipo(tipo: String) {
        vivienda = vivienda.copy(tipo = tipo)
    }

    fun updateViviendaDescripcion(descripcion: String) {
        vivienda = vivienda.copy(descripcion = descripcion)
    }

    fun updateViviendaImagen(imagen: Bitmap?) {
        vivienda = vivienda.copy(imagen = imagen)
    }

    fun closeDialog() {
        openDialog = false
    }

    fun openDialog() {
        openDialog = true
    }

    //#########################################Nota#################################################
    var openNotaDialog by mutableStateOf(false)
    val notas = notaDao.getAllNotas()
    var nota by mutableStateOf(Nota(0, "", "", 0))


    fun addNota(nota: Nota){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.insertNota(nota)
        }
    }

    fun getNota(id: Int){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.getNota(id)
        }
    }

    fun deleteNota(nota: Nota){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.deleteNota(nota)
        }
    }

    fun updateNota(nota: Nota) {
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.updateNota(nota)
        }
    }

    fun getNotaPorVivienda(id: Int){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.getNotasByVivienda(id)
        }
    }

    fun closeNotaDialog() {
        openNotaDialog = false
    }

    fun openNotaDialog() {
        openNotaDialog = true
    }

    //#########################################Inventario############################################

    var openInventarioDialog by mutableStateOf(false)
    var inventario = inventarioDao.getInventario()
    var item = mutableStateOf( Item(0, "", "", 0))


}
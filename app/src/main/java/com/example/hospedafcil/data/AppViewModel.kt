package com.example.hospedafcil.data

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospedafcil.data.daos.InventarioDao
import com.example.hospedafcil.data.daos.NotaDao
import com.example.hospedafcil.data.daos.ViviendaDao
import com.example.hospedafcil.data.tablas.Item
import com.example.hospedafcil.data.tablas.Nota
import com.example.hospedafcil.data.tablas.Vivienda
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun addVivienda(vivienda: Vivienda) {
        viewModelScope.launch(Dispatchers.IO) {
            viviendaDao.insertVivienda(vivienda)
        }
    }

    fun getVivienda(id: Int): Flow<List<Vivienda>> {
        return viviendaDao.getVivienda(id)
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
    var nota by mutableStateOf(Nota(0, "", ""))

    fun addNota(nota: Nota){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.insertNota(nota)
        }
    }

    fun getNota(id: Int): Flow<List<Nota>> {
        return notaDao.getNota(id)
    }

    fun deleteNota(nota: Nota){
        viewModelScope.launch (Dispatchers.IO) {
            notaDao.deleteNota(nota)
        }
    }

    fun updateNota(nota: Nota) {
        viewModelScope.launch(Dispatchers.IO) {
            notaDao.updateNota(nota)
        }
    }

    fun updateNotaAsunto(asunto: String) {
        nota = nota.copy(asunto = asunto)
    }

    fun updateNotaDescripcion(descripcion: String) {
        nota = nota.copy(descripcion = descripcion)
    }

    fun closeNotaDialog() {
        openNotaDialog = false
    }

    fun openNotaDialog() {
        openNotaDialog = true
    }

    //#########################################Inventario############################################

    var openItemDialog by mutableStateOf(false)
    var inventario = inventarioDao.getInventario()
    var item by mutableStateOf( Item(0, "", "", ""))

    fun addItem(item: Item){
        viewModelScope.launch (Dispatchers.IO) {
            inventarioDao.insertItem(item)
        }
    }

    fun getItem(id: Int): Flow<List<Item>> {
        return inventarioDao.getItem(id)
    }

    fun deleteItem(nota: Item){
        viewModelScope.launch (Dispatchers.IO) {
            inventarioDao.deleteItem(nota)
        }
    }

    fun updateItem(nota: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            inventarioDao.updateItem(nota)
        }
    }

    fun updateItemNombre(nombre: String) {
        item = item.copy(nombre = nombre)
    }

    fun updateItemDescripcion(descripcion: String) {
        item = item.copy(descripcion = descripcion)
    }

    fun updateItemCantidad(cantidad: String) {
        item = item.copy(cantidad = cantidad)
    }

    fun closeItemDialog() {
        openItemDialog = false
    }

    fun openItemDialog() {
        openItemDialog = true
    }
}
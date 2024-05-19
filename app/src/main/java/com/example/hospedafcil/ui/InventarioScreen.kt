package com.example.hospedafcil.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.ui.viviendasScreens.componentes.AddViviendaAlertDialog
import com.example.hospedafcil.ui.viviendasScreens.componentes.AddViviendaFloatingActionButton
import com.example.hospedafcil.ui.viviendasScreens.componentes.ViviendasContent

@Composable
fun InventarioScreen(viewModel: AppViewModel){
    val inventario by viewModel.casas.collectAsState(emptyList())
    Scaffold(
        content = { padding ->
            InventarioContent(
                padding = padding,
                viviendas = inventario,
                navigateToUpdateVivienda = navigateToUpdateItem,
                deleteVivienda = { item ->
                    viewModel.deleteItem(item)
                }
            )
            AddItemAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog() },
                addVivienda = { item ->
                    viewModel.addItem(item)
                }
            )
        },
        floatingActionButton = {
            AddItemFloatingActionButton(
                openDialog = {
                    viewModel.openItemDialog()
                }
            )
        }
    )
}


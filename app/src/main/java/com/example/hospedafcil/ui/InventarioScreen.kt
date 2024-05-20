package com.example.hospedafcil.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.ui.inventarioComponents.AddItemAlertDialog
import com.example.hospedafcil.ui.inventarioComponents.AddItemFloatingActionButton
import com.example.hospedafcil.ui.inventarioComponents.InventarioContent

@Composable
fun InventarioScreen(
    navigateToUpdateItem: (Int) -> Unit,
    viewModel: AppViewModel
){
    val inventario by viewModel.inventario.collectAsState(emptyList())
    Scaffold(
        content = { padding ->
            InventarioContent(
                padding = padding,
                inventario = inventario,
                navigateToUpdateItem = navigateToUpdateItem,
                deleteItem = { item ->
                    viewModel.deleteItem(item)
                }
            )
            AddItemAlertDialog(
                openItemDialog = viewModel.openItemDialog,
                closeItemDialog = {
                    viewModel.closeItemDialog() },
                addItem = { item ->
                    viewModel.addItem(item)
                }
            )
        },
        floatingActionButton = {
            AddItemFloatingActionButton(
                openItemDialog = {
                    viewModel.openItemDialog()
                }
            )
        }
    )
}
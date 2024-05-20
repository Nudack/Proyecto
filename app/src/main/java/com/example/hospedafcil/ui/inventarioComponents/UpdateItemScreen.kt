package com.example.hospedafcil.ui.inventarioComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.tablas.Item

@Composable
fun UpdateItemScreen(
    viewModel: AppViewModel,
    itemId: Int,
    navigateBack: () -> Unit
) {

    //###############################Falla recoger Viendas#########################################

    val itemActualList: List<Item> by viewModel.getItem(itemId).collectAsState(initial = emptyList())

    if (itemActualList.isEmpty()) {
        Text(text = "CARGANDO...")
    }

    itemActualList.forEach { item ->
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { navigateBack() },
            title = {
                Text(text = "Actualizar Item")
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = item.nombre,
                        onValueChange = { nombre ->
                            viewModel.updateItemNombre(nombre)
                        },
                        placeholder = {
                            Text(text = "Nombre")
                        },
                        singleLine = true,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = item.descripcion,
                        onValueChange = { descripcion ->
                            viewModel.updateItemDescripcion(descripcion)
                        },
                        placeholder = {
                            Text(text = "Descripción")
                        },
                        singleLine = true,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = item.cantidad,
                        onValueChange = { cantidad ->
                            viewModel.updateItemCantidad(cantidad)
                        },
                        placeholder = {
                            Text(text = "Descripción")
                        },
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.updateItem(item)
                        navigateBack()
                    }) {
                    Text(text = "Actualizar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { navigateBack() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}
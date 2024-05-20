package com.example.hospedafcil.ui.inventarioComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.data.tablas.Item
import kotlinx.coroutines.job

@Composable
fun AddItemAlertDialog(
    openItemDialog: Boolean,
    closeItemDialog: () -> Unit,
    addItem: (nota: Item) -> Unit
){
    if(openItemDialog){
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var cantidad by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = { closeItemDialog() },
            title = {
                Text(text = "Nueva Vivienda")
            },
            text = {
                Column {
                    TextField(
                        value = nombre,
                        onValueChange = {nombre = it},
                        placeholder = {
                            Text(text = "Nombre") },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = cantidad,
                        onValueChange = {cantidad = it},
                        placeholder ={
                            Text(text = "Cantidad")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = descripcion,
                        onValueChange = {descripcion = it},
                        placeholder ={
                            Text(text = "Descripción")
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    closeItemDialog()
                    val nota = Item(id = 0, nombre = nombre, descripcion = descripcion, cantidad = cantidad)
                    addItem(nota)
                }) {
                    Text(text = "Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { closeItemDialog() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}
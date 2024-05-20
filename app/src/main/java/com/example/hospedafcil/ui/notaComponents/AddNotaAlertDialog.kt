package com.example.hospedafcil.ui.notaComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.data.tablas.Nota
import kotlinx.coroutines.job

@Composable
fun AddNotaAlertDialog(
    openNotaDialog: Boolean,
    closeNotaDialog: () -> Unit,
    addNota: (nota: Nota) -> Unit
){
    if(openNotaDialog){
        var asunto by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = { closeNotaDialog() },
            title = {
                Text(text = "Nueva Vivienda")
            },
            text = {
                Column {
                    TextField(
                        value = asunto,
                        onValueChange = {asunto = it},
                        placeholder = {
                            Text(text = "Asunto") },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }

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
                    closeNotaDialog()
                    val nota = Nota(asunto = asunto, descripcion = descripcion)
                    addNota(nota)
                }) {
                    Text(text = "Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { closeNotaDialog() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}
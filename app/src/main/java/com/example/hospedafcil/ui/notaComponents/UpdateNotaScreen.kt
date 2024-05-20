package com.example.hospedafcil.ui.notaComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.tablas.Nota

@Composable
fun UpdateNotaScreen(
    viewModel: AppViewModel,
    notaId: Int,
    navigateBack: () -> Unit
) {
    //###############################Falla recoger Viendas#########################################

    val notaActualList: List<Nota> by viewModel.getNota(notaId).collectAsState(initial = emptyList())

    if (notaActualList.isEmpty()) {
        Text(text = "CARGANDO...")
    }

    notaActualList.forEach { nota ->
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = { navigateBack() },
            title = {
                Text(text = "Actualizar Vivenda")
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = nota.asunto,
                        onValueChange = { asunto ->
                            viewModel.updateNotaAsunto(asunto)
                        },
                        placeholder = {
                            Text(text = "Asunto")
                        },
                        singleLine = true,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = nota.descripcion,
                        onValueChange = { descripcion ->
                            viewModel.updateNotaDescripcion(descripcion)
                        },
                        placeholder = {
                            Text(text = "Descripción")
                        },
                        singleLine = true,
                        maxLines = 1
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.updateNota(nota)
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
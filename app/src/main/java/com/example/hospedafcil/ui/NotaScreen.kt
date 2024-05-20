package com.example.hospedafcil.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.ui.notaComponents.AddNotaAlertDialog
import com.example.hospedafcil.ui.notaComponents.AddNotaFloatingActionButton
import com.example.hospedafcil.ui.notaComponents.NotasContent

@Composable
fun NotaScreen (
    navigateToUpdateNota: (notaId: Int) -> Unit,
    viewModel: AppViewModel
){
    val notas by viewModel.notas.collectAsState(emptyList())
    Scaffold(
        content = { padding ->
            NotasContent(
                padding = padding,
                notas = notas,
                navigateToUpdateNota = navigateToUpdateNota,
                deleteNota = { nota ->
                    viewModel.deleteNota(nota)
                }
            )
            AddNotaAlertDialog(
                openNotaDialog = viewModel.openNotaDialog,
                closeNotaDialog = {
                    viewModel.closeNotaDialog() },
                addNota = { nota ->
                    viewModel.addNota(nota)
                }
            )
        },
        floatingActionButton = {
            AddNotaFloatingActionButton(
                openNotaDialog = {
                    viewModel.openNotaDialog()
                }
            )
        }
    )
}
package com.example.hospedafcil.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.nota.Nota
import kotlinx.coroutines.flow.Flow


@Composable
fun NotaScreen (viewModel: AppViewModel){
    val notas by viewModel.notas.collectAsState(emptyList())
    Scaffold(
        content = { padding ->
            NotasContent(
                padding = padding,
                viviendas = notas,
                navigateToUpdateVivienda = navigateToUpdateNota,
                deleteVivienda = { nota ->
                    viewModel.deleteNota(nota)
                }
            )
            AddNotaAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog() },
                addVivienda = { nota ->
                    viewModel.addNota(nota)
                }
            )
        },
        floatingActionButton = {
            AddNotaFloatingActionButton(
                openDialog = {
                    viewModel.openNotaDialog()
                }
            )
        }
    )
}
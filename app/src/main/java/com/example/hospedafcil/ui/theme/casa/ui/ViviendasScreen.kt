package com.example.hospedafcil.ui.theme.casa.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hospedafcil.ui.theme.casa.viewModel.ViviendasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViviendasScreen(
    viewModel: ViviendasViewModel
) {
    val viviendas by viewModel.viviendas.collectAsState( initial = emptyList() )
    if (viviendas.isEmpty()) {
        // Mostrar un indicador de carga o mensaje de que no hay datos disponibles
        Text(text = "Cargando viviendas...")
    } else {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Viviendas") }) },
            content = { padding ->
                ViviendasContent(
                    padding = padding,
                    viviendas = viviendas
                )
                AddViviendaAlertDialog(
                    openDialog = viewModel.openDialog,
                    closeDialog = {
                        viewModel.closeDialog()
                    },
                    addVivienda = {
                    vivienda ->
                        viewModel.addVivienda(vivienda)
                    }
                )
            },
            floatingActionButton = {
                AddViviendaFloatingActionButton(
                    openDialog = {
                        viewModel.openDialog()
                    }
                )
            }
        )
    }
}

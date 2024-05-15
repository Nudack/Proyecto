package com.example.hospedafcil.ui.app.ui.viviendasScreens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes.AddViviendaAlertDialog
import com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes.AddViviendaFloatingActionButton
import com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes.ViviendasContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViviendasScreen(
    viewModel: AppViewModel
) {
    val viviendas by viewModel.viviendas.collectAsState( initial = emptyList() )
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
                    viewModel.closeDialog() },
                addVivienda = { vivienda ->
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

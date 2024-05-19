package com.example.hospedafcil.ui.viviendasScreens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.ui.viviendasScreens.componentes.AddViviendaAlertDialog
import com.example.hospedafcil.ui.viviendasScreens.componentes.AddViviendaFloatingActionButton
import com.example.hospedafcil.ui.viviendasScreens.componentes.ViviendasContent


@Composable
fun HabitacionScreen(
    viewModel: AppViewModel,
    navigateToUpdateVivienda: (viviendaId: Int) -> Unit
){
    val habitaciones by viewModel.habitaciones.collectAsState(emptyList())
    Scaffold(
        content = { padding ->
            ViviendasContent(
                padding = padding,
                viviendas = habitaciones,
                navigateToUpdateVivienda = navigateToUpdateVivienda,
                deleteVivienda = { vivienda ->
                    viewModel.deleteVivienda(vivienda)
                }
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
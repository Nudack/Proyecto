package com.example.hospedafcil.ui.viviendasScreens.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddViviendaFloatingActionButton(
    openDialog: () -> Unit
) {
    FloatingActionButton( onClick = { openDialog() } ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir Vivienda")
    }
}
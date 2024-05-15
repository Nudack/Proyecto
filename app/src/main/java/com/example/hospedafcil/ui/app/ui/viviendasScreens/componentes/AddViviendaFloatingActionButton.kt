package com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddViviendaFloatingActionButton(
    openDialog: () -> Unit
) {
    FloatingActionButton(
        onClick = { openDialog() },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir Vivienda")
    }
}